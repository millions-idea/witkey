/***
 * @pName proback
 * @name ExpressOrdersService
 * @user HongWei
 * @date 2018/8/4
 * @desc
 */
package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.ExpressOrders;
import com.wanhao.proback.bean.member.ExpressOrdersView;
import com.wanhao.proback.bean.member.MerchantExpressOrdersParam;
import com.wanhao.proback.service.BaseService;

import java.util.List;

public interface ExpressOrdersService extends BaseService<ExpressOrders> {
    /**
     * 获取-不带分页 韦德 2018年8月4日10:06:17
     * @param condition
     * @return
     */
    List<ExpressOrdersView> getOrdersBy(String condition);

    /**
     * 获取-带分页 韦德 2018年8月4日10:06:17
     * @param condition
     * @return
     */
    List<ExpressOrdersView> getOrdersLimit(Integer page, String limit, String condition);

    /**
     * 删除 韦德 2018年8月4日10:06:17
     * @param id
     */
    void deleteBy(String id);

    /**
     * 获取记录总数 韦德 2018年8月4日10:06:17
     * @return
     */
    int getOrdersCount();

    /**
     * 编辑运单号 韦德 2018年8月4日23:24:46
     * @param param
     */
    void updateExpressId(ExpressOrders param);

    /**
     * 发货 韦德 2018年8月5日00:14:32
     * @param param
     */
    void toSendOut(ExpressOrders param);

    /**
     * 批量发货 韦德 2018年8月5日01:04:28
     * @param id
     */
    void batchToSendOut(String id);

    /**
     * 商家添加快递代发订单 韦德 2018年8月8日01:07:47
     * @param merchantExpressOrdersParam
     */
    void addMerchantOrder(MerchantExpressOrdersParam merchantExpressOrdersParam);

    /**
     * 快递发货 韦德 2018年8月8日14:12:44
     * @param orderId
     * @return
     */
    List<String> sendOut(String orderId);
}
