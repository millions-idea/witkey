/***
 * @pName proback
 * @name ExpressPlatform
 * @user HongWei
 * @date 2018/8/1
 * @desc 快递平台管理
 */
package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

@Table(name = "tb_express_platforms")
@Setter
@Getter
@ToString
public class ExpressPlatform {
    private Integer expp_id;
    private String name;
    private String url;
    private Integer isEnabled = 1;
    private Integer isDelete = 0;

    public ExpressPlatform(Integer expp_id, String name, String url, Integer isEnabled, Integer isDelete) {
        this.expp_id = expp_id;
        this.name = name;
        this.url = url;
        this.isEnabled = isEnabled;
        this.isDelete = isDelete;
    }

    public ExpressPlatform() {

    }
}
