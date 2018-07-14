package com.wanhao.proback.bean.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Created by LiuLiHao on 2018/7/14 18:12.
 * 描述： 管理员
 * 作者： LiuLiHao
 */
@Setter
@Getter
@ToString
@Table(name = "tb_admin")//表明
public class Admin {
    private Integer id ;
    private String username;
    private String password;
    private String email;
    private String mobile;

}
