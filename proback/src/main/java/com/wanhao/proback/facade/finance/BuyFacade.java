/***
 * @pName proback
 * @name BuyFacade
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.facade.finance;

import com.wanhao.proback.bean.finance.TransferParam;

public interface BuyFacade {
    /**
     * 购买物品 韦德 2018年8月8日16:10:27
     * @param transferParam
     */
    void buy(TransferParam transferParam);
}
