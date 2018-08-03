/***
 * @pName proback
 * @name BusinessBrandsMapper
 * @user HongWei
 * @date 2018/8/3
 * @desc 品牌电商公司表
 */
package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * 品牌电商公司表
 */
@Table(name = "tb_business_brands")
@Setter
@Getter
@ToString
public class BusinessBrands {
    private Integer business_id;
    private String name;
    private Integer isEnable;
    private Integer isDelete;

    public BusinessBrands(Integer business_id, String name, Integer isEnable, Integer isDelete) {
        this.business_id = business_id;
        this.name = name;
        this.isEnable = isEnable;
        this.isDelete = isDelete;
    }

    public BusinessBrands() {

    }
}
