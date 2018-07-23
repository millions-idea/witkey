package com.wanhao.proback.bean.vip;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Created by LiuLiHao on 2018/7/23 11:32.
 * 描述：
 * 作者： LiuLiHao
 *
 */
@Table(name = "tb_vip_price")
@Setter
@Getter
@ToString
public class Vip extends BaseBean {

    private String vipmodel;
    private String sale_mode;
    private String vip_type;
    private String kuaidi_limit;

    private Integer bai_tian_price;
    private Integer vip_flag;
    private Integer dizhi_limit;
    private Integer day_limit;
    private Integer week_limit;
    private Integer month_limit;
    private Integer sum_price_limit;

}
