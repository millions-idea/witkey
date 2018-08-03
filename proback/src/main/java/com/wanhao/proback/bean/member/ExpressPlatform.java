/***
 * @pName proback
 * @name ExpressPlatform
 * @user HongWei
 * @date 2018/8/1
 * @desc 快递平台渠道表
 */
package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * 快递平台渠道表
 */
@Table(name = "tb_express_platforms")
@Setter
@Getter
@ToString
public class ExpressPlatform {
    private Integer expp_id;
    private String name;
    private String url;
    private Integer isEnable = 0;
    private Integer isDelete = 0;

    public ExpressPlatform(Integer expp_id, String name, String url, Integer isEnable, Integer isDelete) {
        this.expp_id = expp_id;
        this.name = name;
        this.url = url;
        this.isEnable = isEnable;
        this.isDelete = isDelete;
    }

    public ExpressPlatform() {

    }
}
