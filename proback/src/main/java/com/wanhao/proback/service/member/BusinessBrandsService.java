/***
 * @pName proback
 * @name BusinessBrandsService
 * @user HongWei
 * @date 2018/8/3
 * @desc
 */
package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.BusinessBrands;
import com.wanhao.proback.bean.member.ExpressGoodsView;

import java.util.List;

public interface BusinessBrandsService {
    /**
     * 获取电商公司集合 韦德 2018年8月3日11:37:57
     * @param condition
     * @return
     */
    List<BusinessBrands> getBusinessesBy(String condition);
}
