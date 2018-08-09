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
    @JsonView(MerchantView.class)
    private String express_code;

    @JsonView(MerchantView.class)
    private String name;

    @JsonView(MerchantView.class)
    private Double price;
}
