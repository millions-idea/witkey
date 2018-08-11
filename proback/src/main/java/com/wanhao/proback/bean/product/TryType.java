/***
 * @pName proback
 * @name TryType
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.bean.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/***
 * 试用类型表
 */
@Table(name = "tb_try_type")
@Setter
@Getter
@ToString
public class TryType {
    private Integer id;
    private String type_name;
}
