/***
 * @pName proback
 * @name ExpressOrderFacade
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.facade.member.express;

import com.wanhao.proback.bean.member.ExpressOrders;
import com.wanhao.proback.bean.member.ExpressOrdersParam;
import com.wanhao.proback.bean.member.MerchantExpressOrdersParam;

import java.util.List;

/**
 * 快递订单
 */
public interface ExpressOrderFacade {
    /**
     * 下单 韦德 2018年8月8日16:15:25
     * @param param
     */
    void placeOrder(ExpressOrders param);

    /**
     * 下单-包含多件商品 韦德 2018年8月8日17:41:57
     * @param expressOrdersParams
     */
    void placeOrder(List<ExpressOrdersParam> expressOrdersParams);
}
