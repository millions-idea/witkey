package com.wanhao.proback.bean.admin;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiuLiHao on 2018/7/14 18:12.
 * 描述： 管理员
 * 作者： LiuLiHao
 */
@Setter
@Getter
@ToString
@Table(name = "tb_admin")//表明
public class Admin extends BaseBean {
    private String username;
    private String password;
    private String email;
    private String mobile;
    //登录的IP
    private String ip;

    private Integer login_count;
    private Date login_time;
    //可用余额
    private Double money;

}
