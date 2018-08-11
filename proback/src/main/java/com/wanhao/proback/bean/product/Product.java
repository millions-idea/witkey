/***
 * @pName proback
 * @name Product
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

/***
 * 商品表
 */
@Table(name = "tb_products")
@Setter
@Getter
@ToString
public class Product {

    private Integer product_id;
    private String product_code;
    private Integer platform_category_id;
    private Integer product_category_id;
    private Double price;
    private Double return_price;
    private Double append_return_price;
    private Integer merchant_id;
    private String image_url;
    private String product_url;
    private String product_name;
    private Integer order_status;
    private String order_remark;
    private String search_keyword_id_list;
    private String specification;
    private Integer is_insure;
    private Double insure_price;
    private Integer templet_id;
    private String templet_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date add_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date edit_date;
    private Integer is_enable;
    private Integer is_delete;

    public Product() {
    }

    public Product(Integer product_id, String product_code, Integer platform_category_id, Integer product_category_id, Double price, Double return_price, Double append_return_price, Integer merchant_id, String image_url, String product_url, String product_name, Integer order_status, String order_remark, String search_keyword_id_list, String specification, Integer is_insure, Double insure_price, Integer templet_id, String templet_name, Date add_date, Date edit_date, Integer is_enable, Integer is_delete) {

        this.product_id = product_id;
        this.product_code = product_code;
        this.platform_category_id = platform_category_id;
        this.product_category_id = product_category_id;
        this.price = price;
        this.return_price = return_price;
        this.append_return_price = append_return_price;
        this.merchant_id = merchant_id;
        this.image_url = image_url;
        this.product_url = product_url;
        this.product_name = product_name;
        this.order_status = order_status;
        this.order_remark = order_remark;
        this.search_keyword_id_list = search_keyword_id_list;
        this.specification = specification;
        this.is_insure = is_insure;
        this.insure_price = insure_price;
        this.templet_id = templet_id;
        this.templet_name = templet_name;
        this.add_date = add_date;
        this.edit_date = edit_date;
        this.is_enable = is_enable;
        this.is_delete = is_delete;
    }
}
