package com.wanhao.proback.bean.shop;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Created by LiuLiHao on 2018/7/21 14:39.
 * 描述：
 * 作者： LiuLiHao
 */
@Table(name = "tb_shop")
@Setter
@Getter
@ToString
public class Shop extends BaseBean {
    private Integer mem_id;
    private Integer is_pass;
    private String shop_type;
    private String shop_name;
    private String shop_url;
    private String shop_wangwang;
    private String send_province;
    private String send_city;
    private String send_town;
    private String shop_img;
    private String remark;

}
