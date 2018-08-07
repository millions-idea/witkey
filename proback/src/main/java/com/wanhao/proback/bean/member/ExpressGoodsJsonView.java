/***
 * @pName proback
 * @name ExpressGoodsJsonView
 * @user HongWei
 * @date 2018/8/7
 * @desc
 */
package com.wanhao.proback.bean.member;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExpressGoodsJsonView  {
    public interface MerchantView { };

    @JsonView(MerchantView.class)
    private Integer goods_id;
    private Integer expp_id;
    private Integer category_id;

    @JsonView(MerchantView.class)
    private String name;

    @JsonView(MerchantView.class)
    private Double price;
    private Integer isEnable = 0;
    private Integer isDelete = 0;
}
