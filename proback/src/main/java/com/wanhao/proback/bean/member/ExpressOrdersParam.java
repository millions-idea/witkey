/***
 * @pName proback
 * @name ExpressOrdersParam
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ExpressOrdersParam {
    private Integer userId;
    private Integer goodsId;
    private Integer orderId;
}
