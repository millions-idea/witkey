package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by LiuLiHao on 2018/7/14 17:43.
 * 描述： 会员
 * 作者： LiuLiHao
 */
@Setter
@Getter
@ToString
public class Member {
    private Integer uid;
    private String username;
    private String password;
    private String user_ip;
    private String email;
    private String mobile;
    private String img;
    private String qianming;
    private String mobilecode;
    private String reg_key;

}
