/***
 * @pName proback
 * @name PayService
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.finance;


import com.wanhao.proback.bean.finance.Moneys;
import com.wanhao.proback.bean.finance.MoneysView;
import com.wanhao.proback.bean.finance.TransactionsView;
import com.wanhao.proback.bean.finance.TransferParam;
import com.wanhao.proback.bean.member.MemberView;

import java.util.List;

/***
 * 财务交易业务接口
 */
public interface PayService {
    /**
     * 转账
     * @param param
     */
    void transfer(TransferParam param);

    /**
     * 批量转账
     * @param param
     */
    void transfer(List<TransferParam> param);

    /**
     * 查询记录-分页 韦德 2018年8月6日22:05:51
     * @param page
     * @param limit
     * @param condition
     * @param trade_type
     * @param trade_date_begin
     * @param trade_date_end
     * @return
     */
    List<TransactionsView> getTransactionsLimit(Integer page, String limit, String condition
            , Integer trade_type, String trade_date_begin, String trade_date_end);

    /**
     * 查询总记录数 韦德 2018年8月6日22:06:03
     * @return
     */
    int getTransactionsCount();

    /**
     * 取账户的总收入与总支出情况的实时数据 韦德 2018年8月7日00:43:31
     * @param id
     * @return
     */
    MemberView getAccountAmountForDB(Integer id);

    /**
     * 获取账户的总收入与总支出情况的缓存 韦德 2018年8月7日00:43:31
     * @param id
     * @return
     */
    MemberView getAccountAmountForCache(Integer id);


    /**
     * 取账户的总收入与总支出情况 韦德 2018年8月7日00:43:31
     * @param id
     * @return
     */
    MemberView getAccountAmount(Integer id);

    /**
     * 查询资金变化-分页 韦德 2018年8月7日02:14:14
     * @param page
     * @param limit
     * @param condition
     * @param trade_type
     * @param trade_date_begin
     * @param trade_date_end
     * @return
     */
    List<MoneysView> getMoneysLimit(Integer page, String limit, String condition, Integer trade_type, String trade_date_begin, String trade_date_end);

    /**
     * 查询总记录数 韦德 2018年8月7日02:14:01
     * @return
     */
    int getMoneysCount();

    /**
     * 充值 韦德 2018年8月7日03:05:53
     * @param id
     * @param amount
     */
    Boolean recharge(Integer id, Double amount);
}
