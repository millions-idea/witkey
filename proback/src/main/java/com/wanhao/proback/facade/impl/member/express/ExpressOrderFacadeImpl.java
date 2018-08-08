/***
 * @pName proback
 * @name ExpressOrderFacadeImpl
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.facade.impl.member.express;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.wanhao.proback.bean.finance.TransferParam;
import com.wanhao.proback.bean.member.*;
import com.wanhao.proback.facade.member.express.ExpressOrderFacade;
import com.wanhao.proback.service.finance.PayService;
import com.wanhao.proback.service.member.ExpressGoodsService;
import com.wanhao.proback.service.member.ExpressOrdersService;
import com.wanhao.proback.utils.Constants;
import com.wanhao.proback.utils.JsonUtil;
import com.wanhao.proback.utils.PriceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.management.ImmutableDescriptor;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ExpressOrderFacadeImpl implements ExpressOrderFacade {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayService payService;

    @Autowired
    private ExpressOrdersService expressOrdersService;

    @Autowired
    private ExpressGoodsService expressGoodsService;

    /**
     * 下单 韦德 2018年8月8日16:15:25
     *
     * @param param
     */
    @Override
    @Transactional
    public void placeOrder(ExpressOrders param) {
        logger.info("ExpressOrderFacadeImpl_placeOrder" + JsonUtil.getJson(param));

        // 查询商品单价
        ExpressGoodsView goods = expressGoodsService.getById(param.getGoods_id());
        if(goods == null) throw new RuntimeException("商品不存在");

        // 免密支付
        TransferParam transferParam = new TransferParam();
        transferParam.setFromUid(param.getUser_id());
        transferParam.setToUid(Constants.SYSTEM_ACCOUNT);
        transferParam.setTradeType(2);
        transferParam.setAmount(goods.getSell_price());
        transferParam.setRemark("代发快递");
        payService.transfer(transferParam);

        // 发货操作
        expressOrdersService.toSendOut(param);
    }

    /**
     * 下单-包含多件商品 韦德 2018年8月8日17:41:57
     *
     * @param expressOrdersParams
     */
    @Override
    public void placeOrder(List<ExpressOrdersParam> expressOrdersParams) {
        logger.info("ExpressOrderFacadeImpl_placeOrder_array" + JsonUtil.getJson(expressOrdersParams));

        // 查询商品单价
        List<Integer> users = expressOrdersParams.stream().map(ExpressOrdersParam::getGoodsId).collect(toList());
        List<ExpressGoods> goodsList = expressGoodsService.getInIdByUnionAll(users);

        if(goodsList == null || goodsList.size() == 0) throw new RuntimeException("查询商品失败");

        // 免密支付
        List<TransferParam> transferParams = new ArrayList<>();
        for (int i = 0; i < expressOrdersParams.size(); i++) {
            ExpressGoods goods = goodsList.get(i);
            TransferParam transferParam = new TransferParam();
            transferParam.setFromUid(expressOrdersParams.get(i).getUserId());
            transferParam.setToUid(Constants.SYSTEM_ACCOUNT);
            transferParam.setTradeType(2);
            transferParam.setAmount(PriceUtil.getSellPrice(goods.getPrice(), goods.getRate()));
            transferParam.setRemark("代发快递");
            transferParams.add(transferParam);
        }
        
        payService.transfer(transferParams);

        // 发货操作
        List<Integer> orders = expressOrdersParams.stream().map(ExpressOrdersParam::getOrderId).collect(toList());
        String ordersId = Joiner.on(",").skipNulls().join(orders);
        expressOrdersService.batchToSendOut(ordersId);
    }
}
