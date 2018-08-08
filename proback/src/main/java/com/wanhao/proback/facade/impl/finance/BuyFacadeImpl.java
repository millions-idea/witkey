/***
 * @pName proback
 * @name BuyFacadeImpl
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.facade.impl.finance;

import com.wanhao.proback.bean.finance.TransferParam;
import com.wanhao.proback.facade.finance.BuyFacade;
import com.wanhao.proback.service.finance.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyFacadeImpl implements BuyFacade {
    @Autowired
    private PayService payService;

    /**
     * 购买物品 韦德 2018年8月8日16:10:27
     *
     * @param transferParam
     */
    @Override
    public void buy(TransferParam transferParam) {
        payService.transfer(transferParam);
    }
}
