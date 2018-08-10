/***
 * @pName proback
 * @name ProductCategoryService
 * @user HongWei
 * @date 2018/8/10
 * @desc
 */
package com.wanhao.proback.service.product;

import com.wanhao.proback.bean.product.ProductCategory;
import com.wanhao.proback.service.BaseService;

import java.util.List;

public interface ProductCategoryService  extends BaseService<ProductCategory> {
    List<ProductCategory> getCategoryList();
}
