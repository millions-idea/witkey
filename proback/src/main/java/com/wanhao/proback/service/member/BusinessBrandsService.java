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
import com.wanhao.proback.service.BaseService;

import java.util.List;

public interface BusinessBrandsService extends BaseService<BusinessBrands> {
    /**
     * 获取电商公司集合 韦德 2018年8月3日11:37:57
     * @param condition
     * @return
     */
    List<BusinessBrands> getBusinessesBy(String condition);

    /**
     * 获取电商公司集合 韦德 2018年8月3日11:37:57
     * @param condition
     * @return
     */
    List<BusinessBrands> getBusinessesLimit(Integer page, String limit,String condition);

    /**
     * 删除品牌 韦德 2018年8月3日23:24:13
     * @param id
     */
    void deleteBy(String id);

    /**
     * 获取记录总数 韦德 2018年8月3日23:56:22
     * @return
     */
    int getBrandsCount();
}
