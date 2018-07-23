package com.wanhao.proback.bean.member;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

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
    private String catid;
    private String account_type;
    private String account;

    private Integer is_pass;
    private Date create_time;
    private Date pass_time;

    private String remark;
    private Integer version;

    private String shoot_huabei;

    private Integer is_delete;
}
