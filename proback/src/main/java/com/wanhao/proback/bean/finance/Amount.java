/***
 * @pName proback
 * @name Amount
 * @user HongWei
 * @date 2018/8/7
 * @desc
 */
package com.wanhao.proback.bean.finance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Amount {
    private Double amount;
    private Integer trade_type;
}
