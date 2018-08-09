/***
 * @pName proback
 * @name ExpressPostalAddress
 * @user HongWei
 * @date 2018/8/4
 * @desc
 */
package com.wanhao.proback.bean.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;

/**
 * 快递发货地址表
 */
@Table(name = "tb_express_postal_address")
@Setter
@Getter
@ToString
public class ExpressPostalAddress {
    private Integer address_id;
    private Integer user_id;
    private Integer city_id;
    private String postal_code;
    private String address;
    private String real_name;
    private String phone;
    private Integer sort;
    private String remark;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date add_date;

    public ExpressPostalAddress() {
    }

    public ExpressPostalAddress(Integer address_id, Integer user_id, Integer city_id, String postal_code, String address, String real_name, String phone, Integer sort, String remark, Date add_date) {
        this.address_id = address_id;
        this.user_id = user_id;
        this.city_id = city_id;
        this.postal_code = postal_code;
        this.address = address;
        this.real_name = real_name;
        this.phone = phone;
        this.sort = sort;
        this.remark = remark;
        this.add_date = add_date;
    }
}
