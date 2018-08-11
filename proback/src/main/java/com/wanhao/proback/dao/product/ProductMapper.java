/***
 * @pName proback
 * @name ProductMapper
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.dao.product;

import com.wanhao.proback.bean.product.Product;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper extends MyMapper<Product> {
    @Select("SELECT * FROM tb_products WHERE product_code = #{productCode}")
    /**
     * 根据code查询 韦德 2018年8月11日16:23:33
     * @return
     */
    Product selectByCode(@Param("productCode") String productCode);
}
