/***
 * @pName proback
 * @name ProductAttribute
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.bean.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * 商品属性表
 */
@Table(name = "tb_product_attributes")
@Setter
@Getter
@ToString
public class ProductAttribute {
    private Integer attr_id;
    private String product_code;
    private String name;
    private String alias;
    private String value;
    private Integer type;
    private String desc;
    private Double append_return_price;
    private String remark;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date add_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date edit_date;

    private List<ProductAttributeOption> options;


    public ProductAttribute() {
    }

    public ProductAttribute(Integer attr_id, String product_code, String name, String alias, String value, Integer type, String desc, Double append_return_price, String remark, Date add_date, Date edit_date) {

        this.attr_id = attr_id;
        this.product_code = product_code;
        this.name = name;
        this.alias = alias;
        this.value = value;
        this.type = type;
        this.desc = desc;
        this.append_return_price = append_return_price;
        this.remark = remark;
        this.add_date = add_date;
        this.edit_date = edit_date;
    }
}
