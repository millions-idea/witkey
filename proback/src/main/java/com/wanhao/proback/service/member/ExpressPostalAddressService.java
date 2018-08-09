/***
 * @pName proback
 * @name ExpressPostalAddressService
 * @user HongWei
 * @date 2018/8/7
 * @desc
 */
package com.wanhao.proback.service.member;


import com.wanhao.proback.bean.member.ExpressPostalAddress;
import com.wanhao.proback.service.BaseService;

import java.util.List;

public interface ExpressPostalAddressService extends BaseService<ExpressPostalAddress> {

    /**
     * 获取发货地址列表 韦德 2018年8月7日22:56:12
     * @param userId
     * @return
     */
    List<ExpressPostalAddress> getPostalAddresses(Integer userId);

    /**
     * 创建并限制添加数量 韦德 2018年8月9日17:29:47
     * @param param
     */
    void addExpressAddress(ExpressPostalAddress param);
}
