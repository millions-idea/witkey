package com.wanhao.profront.bean.member;

import com.wanhao.profront.bean.BaseBean;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiuLiHao on 2018/7/20 10:20.
 * 描述： 会员银行卡
 * 作者： LiuLiHao
 */
@Table(name = "tb_member_bank")
public class MemberBank extends BaseBean {
    private Integer mem_id;
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

}
