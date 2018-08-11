/***
 * @pName proback
 * @name ProductService
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.service.product;

import com.wanhao.proback.bean.product.Product;
import com.wanhao.proback.bean.product.ProductView;
import com.wanhao.proback.service.BaseService;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    /**
     * 查询 韦德 2018年8月11日16:22:55
     * @param code
     * @return
     */
    ProductView getProductByCode(String code);
}
