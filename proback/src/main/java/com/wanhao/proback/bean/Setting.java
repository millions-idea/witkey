package com.wanhao.proback.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

/**
 * Created by LiuLiHao on 2018/7/21 15:34.
 * 描述：
 * 作者： LiuLiHao
 */
@Table(name = "tb_setting")
@Setter
@Getter
public class Setting extends BaseBean{
    private Integer open_tixian;
    private Integer tixian_count;
    private Double min_money;
    private Double max_money;
    private Double shouxu;
    private Double min_shouxu;
    private Double max_shouxu;
    private String web_name;
    private String web_url;
    private String web_logo;
    private String mobile_logo;
    private String app_logo;
    private String slide_1_img;
    private String slide_2_img;
    private String slide_3_img;
    private String slide_1_url;
    private String slide_2_url;
    private String slide_3_url;
    private String copy_info;
    private String vip_member_name;
    private String money_name;
    private String money_unit;
    private String virtual_name;
    private String virtual_unit;
    private String yongjin_name;
    private Integer message_open;
    private String alipay_qrcode;
    private String goods_default_img;


    private Double zhijie_fabu_shiyong;
    private Double jianjie_fabu_shiyong;
    private Double zhijie_shenqing_shiyong;
    private Double jianjie_shenqing_shiyong;
    private Double zhijie_fabu_shiyong_fw;
    private Double jianjie_fabu_shiyong_fw;
    private Double shangjia_wancheng_yiji;
    private Double shike_wancheng_yiji;
    private Double shike_wancheng_erji;
    private Double yiji_jiangli;
    private Double erji_jiangli;

}
