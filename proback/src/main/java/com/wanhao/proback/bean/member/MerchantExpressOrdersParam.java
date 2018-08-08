/***
 * @pName proback
 * @name MerchantExpressOrdersParam
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MerchantExpressOrdersParam {
    private Integer user_id;
    private Integer send_address_id;
    private String recv_address;
    private Double weight;
    private Integer goods_id;
    private String remark;
}
