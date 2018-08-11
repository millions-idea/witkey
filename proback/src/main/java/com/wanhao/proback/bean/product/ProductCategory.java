/***
 * @pName proback
 * @name ProductCategorys
 * @user HongWei
 * @date 2018/8/10
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

/**
 * 商品分类表
 */
@Table(name = "tb_product_categorys")
@Setter
@Getter
@ToString
public class ProductCategory {
    private Integer category_id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date add_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date edit_date;
    private Integer is_enable;
    private Integer is_delete;

}
