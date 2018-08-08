/***
 * @pName proback
 * @name ExpressOrdersView
 * @user HongWei
 * @date 2018/8/4
 * @desc
 */
package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@ToString
public class ExpressOrdersView extends ExpressOrders {
    private String username;
    private String address_remark;
    private String send_address;
    private String orders_remark;
    private String real_name;
    private String phone;
    private String status_text;
    private String goods_name;

    public ExpressOrdersView() {
    }

    public ExpressOrdersView(String username, String address_remark, String send_address, String orders_remark, String real_name, String phone, String status_text) {
        this.username = username;
        this.address_remark = address_remark;
        this.send_address = send_address;
        this.orders_remark = orders_remark;
        this.real_name = real_name;
        this.phone = phone;
        this.status_text = status_text;
    }

    public ExpressOrdersView(Integer order_id, Integer user_id, Integer goods_id, Integer send_address_id, String recv_address, Double weight, String express_id, Double amount, Integer status, Date add_date, Date edit_date, String remark, Integer isEnable, Integer isDelete, String username, String address_remark, String send_address, String orders_remark, String real_name, String phone, String status_text, String goods_name) {
        super(order_id, user_id, goods_id, send_address_id, recv_address, weight, express_id, amount, status, add_date, edit_date, remark, isEnable, isDelete);
        this.username = username;
        this.address_remark = address_remark;
        this.send_address = send_address;
        this.orders_remark = orders_remark;
        this.real_name = real_name;
        this.phone = phone;
        this.status_text = status_text;
        this.goods_name = goods_name;
    }
}
