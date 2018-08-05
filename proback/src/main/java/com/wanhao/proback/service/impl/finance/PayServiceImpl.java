/***
 * @pName proback
 * @name PayServiceImpl
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.impl.finance;

import com.wanhao.proback.annotaion.AspectLog;
import com.wanhao.proback.bean.finance.TransferParam;
import com.wanhao.proback.bean.member.Moneys;
import com.wanhao.proback.bean.member.Transactions;
import com.wanhao.proback.bean.member.Wallets;
import com.wanhao.proback.dao.finance.MoneysMapper;
import com.wanhao.proback.dao.finance.TransactionsMapper;
import com.wanhao.proback.dao.finance.WalletsMapper;
import com.wanhao.proback.exception.FinanceException;
import com.wanhao.proback.service.finance.PayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/***
 * 财务交易业务实现类
 */
@Service
public class PayServiceImpl extends FinanceAbstract implements PayService {

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
        Moneys reduceMoney = new Moneys();
        reduceMoney.setRecord_id(recordId);
        reduceMoney.setFrom_uid(param.getFromUid());
        reduceMoney.setTrade_type(1);
        reduceMoney.setTrade_amount(param.getAmount());
        reduceMoney.setRemark("收入");
        moneyList.add(reduceMoney);

        Moneys addMoney = new Moneys();
        addMoney.setRecord_id(recordId);
        addMoney.setFrom_uid(param.getToUid());
        addMoney.setTrade_type(2);
        addMoney.setTrade_amount(param.getAmount());
        addMoney.setRemark("支出");
        moneyList.add(addMoney);
        count = 0;
        count = moneysMapper.batchInsert(moneyList);
        if(count <= 0) throw new FinanceException(FinanceException.Errors.WALLET_BALANCE_LOG, "资金变化更新失败");
    }
}
