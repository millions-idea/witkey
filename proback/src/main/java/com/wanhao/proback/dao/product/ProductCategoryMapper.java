/***
 * @pName proback
 * @name ProductCategoryMapper
 * @user HongWei
 * @date 2018/8/10
 * @desc
 */
package com.wanhao.proback.dao.product;

import com.wanhao.proback.bean.product.ProductCategory;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductCategoryMapper extends MyMapper<ProductCategory> {
}
