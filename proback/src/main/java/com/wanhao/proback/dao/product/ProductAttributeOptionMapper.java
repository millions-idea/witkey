/***
 * @pName proback
 * @name ProductAttributeOptionMapper
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.dao.product;

import com.wanhao.proback.bean.product.ProductAttributeOption;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductAttributeOptionMapper extends MyMapper<ProductAttributeOption> {

    @Select("SELECT * FROM tb_product_attribute_options WHERE attr_id IN (${attrId})")
    /**
     * 根据属性id查询属性选项列表 韦德 2018年8月11日17:01:23
     * @param attrId
     * @return
     */
    List<ProductAttributeOption> selectByAttrId(@Param("attrId") String attrId);
}
