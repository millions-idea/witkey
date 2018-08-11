/***
 * @pName proback
 * @name ProductAttributeService
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.service.product;

import com.wanhao.proback.bean.product.ProductAttribute;
import com.wanhao.proback.service.BaseService;

import java.util.List;

public interface ProductAttributeService extends BaseService<ProductAttribute> {
    /**
     * 根据商品id查询属性列表 韦德 2018年8月11日16:27:37
     * @param productCode
     * @return
     */
    List<ProductAttribute> getAttributesByCode(String productCode);
}
