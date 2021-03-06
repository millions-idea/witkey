package com.wanhao.proback.bean.member;

import com.wanhao.proback.bean.BaseBean;
import com.wanhao.proback.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by LiuLiHao on 2018/7/14 17:43.
 * 描述： 会员
 * 作者： LiuLiHao
 */
@Setter
@Getter
@ToString
@Table(name = "tb_member") //表明
public class Member extends BaseBean {
    private String username;
    private String password;
    private String login_ip;
    private String email;
    private String mobile;
    private String img;
    //去掉账户金额
    //private Double money;

    //手机验证码
    private String mobile_code;
    //修改密码验证码
    private String pass_code;
    //邀请码
    private Integer invite_id;

    private Date login_time;
    //状态 0正常 1禁用
    private Integer status;
    //省份
    private String sheng;
    //充值金额
    private Double add_moneys;
    //真实姓名
    private String real_name;
    //性别
    private String gender;
    //短信息
    private Integer short_msg;
    //注册时间
    private Date regist_time;
    //登录次数
    private Integer loin_count;
    //登录
    private String proxy;
    //身份证号
    private String id_card;
    //电话
    private String phone;
    //qq
    private String qq;
    //备注信息
    private String remark;
    //注册IP
    private String regist_ip;
    //完成任务次数
    private Integer do_job_count;
    //保证金
    private Double bail_money;
    //投诉次数
    private Integer complain_count;
    //支付宝
    private String alipay;
    //差评
    private Integer bad_comment;
    private Integer good_comment;
    private Integer mid_comment;
    //是否通过考试
    private Integer pass_exam;
    //银行卡号
    private String bank_num;
    //银行类型
    private String bank_type;
    //银行地址
    private String bank_addr;
    //是否实名0未提交 1提交了 2通过 3拒绝
    private Integer is_real_name;
    private Integer is_real_mobile;
    private Integer is_real_bank;
    private Integer is_seller;
    //用户类型
    private String vipmodel;
    private String zheng;
    private String fan;
    private String shou_chi;
    //实名认证提交时间
    private Date real_name_time;
    private Integer version=1;
    //是否绑定了买号  1是 0否
    private Integer is_bind_buy_account;
    private String shi;
    private String token;
    private String pay_pass;
    private Integer is_bind_shop;
    private Double permis_money;

    public Member() {
    }
    public Member(Integer id){
        setId(id);
    }

    private Integer is_delete;

    private String real_name_reason;

    @Transient
    private String memRegistTime;

    public String getMemRegistTime(){
        return regist_time==null? "无": DateUtil.getFormatTime(regist_time);
    }


}
