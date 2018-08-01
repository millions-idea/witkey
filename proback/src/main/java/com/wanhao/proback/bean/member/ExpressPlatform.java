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
    private Integer exp_id;
    private String platform_name;
    private String platform_url;

    public ExpressPlatform() {
    }

    public ExpressPlatform(Integer exp_id, String platform_name, String platform_url) {

        this.exp_id = exp_id;
        this.platform_name = platform_name;
        this.platform_url = platform_url;
    }
}
