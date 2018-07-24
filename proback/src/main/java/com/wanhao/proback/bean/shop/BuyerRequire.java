package com.wanhao.proback.bean.shop;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Created by LiuLiHao on 2018/7/23 20:45.
 * 描述：
 * 作者： LiuLiHao
 */
@Table(name = "tb_buyer_require")
@Setter
@Getter
@ToString
public class BuyerRequire extends BaseBean {
    private Integer day_limit;
    private Integer shangbao_limit;
    private Integer taoqi_limit;
    private Integer gender_limit;
    private Integer memid;

    private Integer goods_id;

    private String forbidden_area;
    private String honor_limit;
    private String age_limit;
    private String always_buy_class;

}
