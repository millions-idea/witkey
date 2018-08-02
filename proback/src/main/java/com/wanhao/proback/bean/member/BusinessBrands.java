/***
 * @pName proback
 * @name BusinessBrandsMapper
 * @user HongWei
 * @date 2018/8/3
 * @desc 电商公司品牌表
 */
package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * 电商公司品牌表
 */
@Table(name = "tb_business_brands")
@Setter
@Getter
@ToString
public class BusinessBrands {
    private Integer business_id;
    private String name;
}
