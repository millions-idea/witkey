/***
 * @pName proback
 * @name ExpressNetParam
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 快递网络请求参数
 */
@Getter
@Setter
@ToString
public class ExpressHttpParam {
    private String username;
    private String password;
    private String expressId;
    private Object body;
}
