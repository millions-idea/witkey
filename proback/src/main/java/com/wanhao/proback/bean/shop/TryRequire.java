package com.wanhao.proback.bean.shop;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
/**
 * Created by LiuLiHao on 2018/7/23 20:50.
 * 描述：
 * 作者： LiuLiHao
 */
@Table(name = "tb_try_require")
@Setter
@Getter
@ToString
public class TryRequire extends BaseBean {

    private Integer zhiding_pinglun;
    private Integer bask_img;
    private Integer task_rec_time;
    private Integer memid;
    private Integer goods_id;
    private String device;
    private Integer need_bi_san_jia;
    private Integer need_chat;
    private Integer need_look_comment;
    private Integer need_add_buy_cart;
    private Integer need_col_goods;
    private String yuyue_xiadan;
    private String comment_content;
    private String confirm_time_type;
    private String remark;

}

