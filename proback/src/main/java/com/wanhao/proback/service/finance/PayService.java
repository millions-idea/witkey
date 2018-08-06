/***
 * @pName proback
 * @name PayService
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.finance;


import com.wanhao.proback.bean.finance.TransactionsView;
import com.wanhao.proback.bean.finance.TransferParam;

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
}
