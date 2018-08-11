/***
 * @pName proback
 * @name ProductAttributeMapper
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.dao.product;

import com.wanhao.proback.bean.product.ProductAttribute;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductAttributeMapper extends MyMapper<ProductAttribute>{
    @Select("SELECT * FROM tb_product_attributes WHERE product_code = #{productCode} ORDER BY sort ASC")
    /**
     * 根据商品code查询属性列表 韦德 2018年8月11日16:28:07
     * @param productCode
     * @return
     */
    List<ProductAttribute> selectByCode(@Param("productCode") String productCode);

}
