package com.wanhao.profront.bean.member;

import com.wanhao.profront.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Created by LiuLiHao on 2018/7/18 17:25.
 * 描述： 淘宝买号表
 * 作者： LiuLiHao
 */
@Table(name = "tb_member_taobao")
@Setter
@Getter
@ToString
public class MemberTaoBao extends BaseBean {
    private Integer mem_id;
    private String tb_account;
    private String age_range;
    private String shoot_real_name;
    private String shoot_zhi_ma;
    private String account_gender;
    private String honor;
    private String shoot_honor;
    private Integer age_start;
    private Integer age_end;
    private Integer taoqi;
    private String province;
    private String city;
    private String town;
    private String shoot_taoqi;
    private String always_class;
    private String truename;
    private String mobile;
    private String pro_name;
    private String city_name;
    private String address;
    private String jd_account;
    private String alibaba_account;
    private String pinduoduo_account;
    private String mogujie_account;
    private String meilishuo_account;
    private String catid;

}
