/***
 * @pName proback
 * @name ProductAttributeOption
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.bean.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;


/***
 * 属性选项表
 */
@Table(name = "tb_product_attribute_options")
@Setter
@Getter
@ToString
public class ProductAttributeOption {
    private Integer option_id;
    private Integer attr_id;
    private String value;
    private String default_value;
    private Integer style;
}
