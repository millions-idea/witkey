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
}
