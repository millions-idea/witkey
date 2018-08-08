/***
 * @pName proback
 * @name ExpressGoods
 * @user HongWei
 * @date 2018/8/2
 * @desc 快递平台商品表
 */
package com.wanhao.proback.bean.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * 快递平台商品表
 */
@Table(name = "tb_express_goods")
@Setter
@Getter
@ToString
public class ExpressGoods {
    private Integer goods_id;
    private Integer expp_id;
    private Integer category_id;
    private String express_code;
    private String name;
    private Double price;
    private Integer rate;
    private Integer isEnable = 0;
    private Integer isDelete = 0;

    public ExpressGoods() {
    }

    public ExpressGoods(Integer goods_id, Integer expp_id, Integer category_id, String express_code, String name, Double price, Integer rate, Integer isEnable, Integer isDelete) {

        this.goods_id = goods_id;
        this.expp_id = expp_id;
        this.category_id = category_id;
        this.express_code = express_code;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.isEnable = isEnable;
        this.isDelete = isDelete;
    }
}
