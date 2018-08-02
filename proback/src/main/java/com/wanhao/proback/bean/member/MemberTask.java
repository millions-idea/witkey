package com.wanhao.proback.bean.member;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by LiuLiHao on 2018/7/26 17:47.
 * 描述： 任务
 * 作者： LiuLiHao
 */
@Table(name = "tb_member_task")
@Setter
@Getter
@ToString
public class MemberTask extends BaseBean {
    private Integer buy_memid;
    private Integer sell_memid;
    private Integer goods_id;
    private Integer buy_account_id;
    private Integer is_finsh;
    private Integer current_step;
    //是否投诉了
    private Integer is_tousu;
    //任务当前状态
    private Integer task_flag;

    private Double goods_price;
    //奖励
    private Double reward_money;

    private String order_num;
    private String seller_name;
    private String shoper_name;

    private String buyer_name;

    private String requirements;
    private String remark;
    private Date finsh_time;
    //一共有多少步
    private Integer all_step;

    private String shoot_huobisanjia;
    private String shoot_search_result;
    private String shoot_chat;

    private Double real_pay_price;
    private Integer shoper_check;

    private Date pick_time;

    private Integer is_cancel;

    private Integer appraise_buy2sell;
    private Integer appraise_sell2buy;

    @Transient
    private Integer day_limit;

}
