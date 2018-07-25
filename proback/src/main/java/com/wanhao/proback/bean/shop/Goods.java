package com.wanhao.proback.bean.shop;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by LiuLiHao on 2018/7/23 18:37.
 * 描述： 商品
 * 作者： LiuLiHao
 */
@Table(name = "tb_goods")
@Setter
@Getter
@ToString
public class Goods extends BaseBean {

    private String catetype;
    private String link_url;
    private String goods_img;
    private String search_word;
    private String goods_format;
    private String template_name;
    private Integer shop_id;
    private Integer goods_class_id;
    private Integer search_type;
    private Integer task_count;
    private Integer return_type;
    private Integer pay_type;
    private Integer time_divider;
    private Integer task_safe;
    private Integer save_template;
    private Integer memid;
    private Double price;
    private Double append_money;
    @Transient
    private TryRequire tryRequire;
    @Transient
    private BuyerRequire buyerRequire;


}
