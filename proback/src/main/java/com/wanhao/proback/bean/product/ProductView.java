/***
 * @pName proback
 * @name ProductView
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.bean.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Map;

/***
 * 商品表视图
 */
@Setter
@Getter
@ToString
public class ProductView {
    private Product product;
    private List<ProductAttribute> attributes;

}
