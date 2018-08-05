/***
 * @pName proback
 * @name PayService
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.finance;


import com.wanhao.proback.bean.finance.TransferParam;

/***
 * 财务交易业务接口
 */
public interface PayService {
    /**
     * 转账
     * @param transferParam
     */
    void transfer(TransferParam transferParam);
}
