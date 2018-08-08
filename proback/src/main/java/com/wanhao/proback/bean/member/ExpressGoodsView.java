/***
 * @pName proback
 * @name ExpressGoodsView
 * @user HongWei
 * @date 2018/8/3
 * @desc
 */
package com.wanhao.proback.bean.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Table(name = "tb_express_goods")
@Setter
@Getter
public class ExpressGoodsView extends ExpressGoods {
    private String category_name;
    private String expp_name;
    private Double sell_price;
    private Double diff_price;
}
