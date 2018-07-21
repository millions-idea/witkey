package com.wanhao.proback.bean.member;


import com.wanhao.proback.bean.BaseBean;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiuLiHao on 2018/7/20 10:20.
 * 描述： 会员银行卡
 * 作者： LiuLiHao
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Table(name = "tb_member_bank")
public class MemberBank extends BaseBean {
    private Integer mem_id;
    /**
     * 是否审核通过 1是 0否 2拒绝
     */
    private Integer is_auth;
    private String bank_type;
    private String bank_num;
    private String bank_mobile;
    private String bank_username;
    private String bank_id_card;
    private Date create_time;
    private Date auth_time;
    private String remark;
    private Integer version;

    private String alipay;
    private String bank_province;
    private String bank_city;
    private String bank_addr;


}
