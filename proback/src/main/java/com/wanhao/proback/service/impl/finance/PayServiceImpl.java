/***
 * @pName proback
 * @name PayServiceImpl
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.impl.finance;

import com.wanhao.proback.annotaion.AspectLog;
import com.wanhao.proback.bean.finance.*;
import com.wanhao.proback.bean.member.MemberView;
import com.wanhao.proback.dao.finance.MoneysMapper;
import com.wanhao.proback.dao.finance.TransactionsMapper;
import com.wanhao.proback.dao.finance.WalletsMapper;
import com.wanhao.proback.dao.utils.ConditionUtil;
import com.wanhao.proback.exception.FinanceException;
import com.wanhao.proback.service.finance.PayService;
import com.wanhao.proback.utils.Constants;
import com.wanhao.proback.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/***
 * 财务交易业务实现类
 */
@Service
public class PayServiceImpl extends FinanceAbstract implements PayService {

    @Autowired
    private RedisTemplate redisTemplate;

    protected PayServiceImpl(TransactionsMapper transactionsMapper, MoneysMapper moneysMapper, WalletsMapper walletsMapper) {
        super(transactionsMapper, moneysMapper, walletsMapper);
    }

    /**
     * 转账
     *
     * @param param
     */
    @Override
    @AspectLog(description = "转账")
    @Transactional
    public void transfer(TransferParam param) {
        // 生成交易流水
        String recordId = UUID.randomUUID().toString();
        Transactions transactions = new Transactions();
        transactions.setFrom_uid(param.getFromUid());
        transactions.setTo_uid(param.getToUid());
        transactions.setRecord_id(recordId);
        transactions.setRecord_no(param.getRecordNo());
        transactions.setTrade_amount(param.getAmount());
        transactions.setRemark(param.getRemark());
        transactions.setTrade_type(param.getTradeType());
        transactions.setTrade_date(new Date());
        int count = transactionsMapper.insert(transactions);
        if(count <= 0) throw new FinanceException(FinanceException.Errors.CREATE_TRANSACTION, "生成流水账单失败");

        // 更新钱包余额
        Wallets wallets = walletsMapper.selectOneByUid(param.getFromUid());

        // 判断操作类型 1=加款 2=扣款 切记要双向操作
        if (param.getTradeType() == 1){
            // 充值
            count = 0;
            count = walletsMapper.addBalance(param.getToUid(), param.getAmount());
            if(count <= 0) throw new FinanceException(FinanceException.Errors.WALLET_ADD_ERROR, "加款失败");
            // 消费
            count = 0;
            count = walletsMapper.reduceBalance(param.getFromUid(), param.getAmount(), wallets.getVersion());
            if(count <= 0) throw new FinanceException(FinanceException.Errors.WALLET_REDUCE_ERROR, "扣款失败");
        }else if (param.getTradeType() == 2){
            wallets = walletsMapper.selectOneByUid(param.getToUid());

            // 消费
            count = 0;
            count = walletsMapper.reduceBalance(param.getToUid(), param.getAmount(), wallets.getVersion());
            if(count <= 0) throw new FinanceException(FinanceException.Errors.WALLET_REDUCE_ERROR, "扣款失败");
            // 充值
            count = 0;
            count = walletsMapper.addBalance(param.getFromUid(), param.getAmount());
            if(count <= 0) throw new FinanceException(FinanceException.Errors.WALLET_ADD_ERROR, "加款失败");
        }

        // 生成资金变化日志
        List<Moneys> moneyList = new ArrayList<>();

        Moneys addMoney = new Moneys();
        addMoney.setRecord_id(recordId);
        addMoney.setFrom_uid(param.getToUid());
        addMoney.setTrade_type(1);
        addMoney.setTrade_amount(param.getAmount());
        addMoney.setRemark("收入");
        moneyList.add(addMoney);

        Moneys reduceMoney = new Moneys();
        reduceMoney.setRecord_id(recordId);
        reduceMoney.setFrom_uid(param.getFromUid());
        reduceMoney.setTrade_type(2);
        reduceMoney.setTrade_amount(param.getAmount());
        reduceMoney.setRemark("支出");
        moneyList.add(reduceMoney);


        count = 0;
        count = moneysMapper.batchInsert(moneyList);
        if(count <= 0) throw new FinanceException(FinanceException.Errors.WALLET_BALANCE_LOG, "资金变化更新失败");
    }

    /**
     * 查询记录-分页 韦德 2018年8月6日22:05:51
     *
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    @Override
    public List<TransactionsView> getTransactionsLimit(Integer page, String limit, String condition
            , Integer trade_type, String trade_date_begin, String trade_date_end) {
        // 计算分页位置
        page = extractPageIndex(page, limit);

        // 查询模糊条件
        String where = extractQueryLike(condition);

        // 查询全部数据或者只有一类数据
        where = extractQueryAllOrOne(trade_type, where);

        // 取两个日期之间或查询指定日期
        where = extractQueryBetweenDate(trade_date_begin, trade_date_end, where);

        List<TransactionsView> list = transactionsMapper.selectLimit(page, limit, trade_type, trade_date_begin, trade_date_end, where);

        return list;
    }

    /**
     * 查询总记录数 韦德 2018年8月6日22:06:03
     *
     * @return
     */
    @Override
    public int getTransactionsCount() {
        return transactionsMapper.count();
    }

    /**
     * 统计账户的总收入与总支出情况 韦德 2018年8月7日00:43:31
     *
     * @param id
     * @return
     */
    @Override
    public MemberView getAccountAmountForDB(Integer id) {
        List<Amount> list = transactionsMapper.getAccountAmount(id);
        if(!list.isEmpty()){
            Amount incomeAmount = list.stream().filter(a -> a.getTrade_type().equals(1)).findFirst().get();
            Amount expendAmount = list.stream().filter(a -> a.getTrade_type().equals(2)).findFirst().get();
            if(incomeAmount != null && expendAmount != null){
                MemberView memberView = new MemberView();
                memberView.setIncomeAmount(incomeAmount.getAmount());
                memberView.setExpendAmount(expendAmount.getAmount());
                try {
                    redisTemplate.opsForValue().set("accountAmount_" + id, JsonUtil.getJson(memberView), 45, TimeUnit.MINUTES);
                }catch (Exception e){
                    System.err.println(e.toString());
                }
                return memberView;
            }
        }
        return null;
    }

    /**
     * 获取账户的总收入与总支出情况的缓存 韦德 2018年8月7日00:43:31
     *
     * @param id
     * @return
     */
    @Override
    public MemberView getAccountAmountForCache(Integer id) {
        Object obj = null;
        try{
            obj = redisTemplate.opsForValue().get("accountAmount_" + id);
        }catch (Exception e) {
            System.err.println(e.toString());
        }
        if(obj != null) return JsonUtil.getModel(String.valueOf(obj), MemberView.class);
        return null;
    }

    /**
     * 取账户的总收入与总支出情况 韦德 2018年8月7日00:43:31
     *
     * @param id
     * @return
     */
    @Override
    public MemberView getAccountAmount(Integer id) {
        MemberView memberView = this.getAccountAmountForCache(id);
        if(memberView == null) memberView = this.getAccountAmountForDB(id);
        return memberView;
    }

    /**
     * 查询资金变化-分页 韦德 2018年8月7日02:14:14
     *
     * @param page
     * @param limit
     * @param condition
     * @param trade_type
     * @param trade_date_begin
     * @param trade_date_end
     * @return
     */
    @Override
    public List<MoneysView> getMoneysLimit(Integer page, String limit, String condition, Integer trade_type, String trade_date_begin, String trade_date_end) {
        // 计算分页位置
        page = extractPageIndex(page, limit);

        // 查询模糊条件
        String where = " 1=1";
        if(condition != null) {
            where += " AND (" + ConditionUtil.like("log_id", condition, true, "t1");
            where += " OR " + ConditionUtil.like("record_id", condition, true, "t1");
            where += " OR " + ConditionUtil.like("username", condition, true, "t2");
            if (condition.split("-").length == 2){
                where += " OR " + ConditionUtil.like("add_date", condition, true, "t1");
            }
            where += " OR " + ConditionUtil.like("trade_type", condition, true, "t1");
            where += " OR " + ConditionUtil.like("remark", condition, true, "t1") + ")";
        }

        // 查询全部数据或者只有一类数据
        where = extractQueryAllOrOne(trade_type, where);

        // 取两个日期之间或查询指定日期
        if ((trade_date_begin != null && trade_date_begin.contains("-")) &&
                trade_date_end != null && trade_date_end.contains("-")){
            where += " AND t1.add_date BETWEEN #{beginTime} AND #{endTime}";
        }else if (trade_date_begin != null && trade_date_begin.contains("-")){
            where += " AND t1.add_date BETWEEN #{beginTime} AND #{endTime}";
        }else if (trade_date_end != null && trade_date_end.contains("-")){
            where += " AND t1.add_date BETWEEN #{beginTime} AND #{endTime}";
        }
        List<MoneysView> list = moneysMapper.selectLimit(page, limit, trade_type, trade_date_begin, trade_date_end, where);

        return list;
    }

    /**
     * 查询总记录数 韦德 2018年8月7日02:14:01
     *
     * @return
     */
    @Override
    public int getMoneysCount() {
        return moneysMapper.count();
    }

    /**
     * 充值 韦德 2018年8月7日03:05:53
     *
     * @param id
     * @param amount
     */
    @Override
    public Boolean recharge(Integer id, Double amount) {
        TransferParam transferParam = new TransferParam();
        transferParam.setFromUid(Constants.SYSTEM_ACCOUNT);
        transferParam.setToUid(id);
        transferParam.setAmount(amount);
        transferParam.setRemark("充值");
        transferParam.setTradeType(1);
        this.transfer(transferParam);
        return true;
    }


    /**
     * 计算分页位置
     * @param page
     * @param limit
     * @return
     */
    private Integer extractPageIndex(Integer page, String limit) {
        if(!limit.equalsIgnoreCase("-1")){
            page = page - 1;
            if (page != 0){
                page = page * Integer.valueOf(limit);
            }
        }
        return page;
    }

    /**
     * 查询模糊条件
     * @param condition
     * @return
     */
    private String extractQueryLike(String condition) {
        String where = " 1=1";
        if(condition != null) {
            where += " AND (" + ConditionUtil.like("record_id", condition, true, "t1");
            where += " OR " + ConditionUtil.like("record_no", condition, true, "t1");
            where += " OR " + ConditionUtil.like("username", condition, true, "t2");
            where += " OR " + ConditionUtil.like("username", condition, true, "t3");
            if (condition.split("-").length == 2){
                where += " OR " + ConditionUtil.like("trade_date", condition, true, "t1");
            }
            where += " OR " + ConditionUtil.like("trade_type", condition, true, "t1");
            where += " OR " + ConditionUtil.like("remark", condition, true, "t1") + ")";
        }
        return where;
    }

    /**
     * 查询全部数据或者只有一类数据
     * @param trade_type
     * @param where
     * @return
     */
    private String extractQueryAllOrOne(Integer trade_type, String where) {
        if (trade_type != null && trade_type != 0){
            where += " AND t1.trade_type = #{trade_type}";
        }
        return where;
    }

    /**
     * 查询两个日期之间的数据
     * @param trade_date_begin
     * @param trade_date_end
     * @param where
     * @return
     */
    private String extractQueryBetweenDate(String trade_date_begin, String trade_date_end, String where) {
        if ((trade_date_begin != null && trade_date_begin.contains("-")) &&
                trade_date_end != null && trade_date_end.contains("-")){
            where += " AND t1.trade_date BETWEEN #{beginTime} AND #{endTime}";
        }else if (trade_date_begin != null && trade_date_begin.contains("-")){
            where += " AND t1.trade_date BETWEEN #{beginTime} AND #{endTime}";
        }else if (trade_date_end != null && trade_date_end.contains("-")){
            where += " AND t1.trade_date BETWEEN #{beginTime} AND #{endTime}";
        }
        return where;
    }
}
