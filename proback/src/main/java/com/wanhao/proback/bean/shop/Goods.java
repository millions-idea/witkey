package com.wanhao.proback.bean.shop;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;

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
    //下单的淘口令
    private String tao_kouling;
    private Integer zhiding_pinglun;
    private Integer bask_img;
    private Integer task_rec_time;
    private String device;
    private Integer need_bi_san_jia;
    private Integer need_chat;
    private Integer need_look_comment;
    private Integer need_add_buy_cart;
    private Integer need_col_goods;
    private Integer is_yuyue_xiadan;
    private String yuyue_xiadan;
    private String comment_content;
    private String confirm_time_type;
    private String remark;
    private Integer day_limit;
    private Integer shangbao_limit;
    private Integer taoqi_limit;
    private Integer gender_limit;
    private String forbidden_area;
    private String honor_limit;
    private String age_limit;
    private String always_buy_class;

    private Integer yuancheng_type;

    @Transient
    private List<String> requires;

    @Transient
    private List<String> color_class;

    @Transient
    private HashMap<String,String> hashMap;

    @Transient
    private String orderBY;
}
