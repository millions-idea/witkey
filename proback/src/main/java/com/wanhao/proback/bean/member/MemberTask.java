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
    private Integer memid;
    private Integer goods_id;
    private Integer buy_account_id;
    private Integer is_finsh;
    private Integer current_step;
    private Date pick_time;

    private Integer is_cancel;

    @Transient
    private Integer day_limit;

}
