/***
 * @pName proback
 * @name ProductAttributeOptionService
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.service.product;

import com.wanhao.proback.bean.product.ProductAttributeOption;
import com.wanhao.proback.service.BaseService;

import java.util.List;

public interface ProductAttributeOptionService extends BaseService<ProductAttributeOption> {
    /**
     * 根据属性id查询属性选项列表 韦德 2018年8月11日16:59:49
     * @param attrId
     * @return
     */
    List<ProductAttributeOption> selectByAttrId(String attrId);
}
