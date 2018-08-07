/***
 * @pName proback
 * @name ExpressOrders
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
 * 快递平台订单表
 */
@Table(name = "tb_express_orders")
@Setter
@Getter
@ToString
public class ExpressOrders {
    private Integer order_id;
    private Integer user_id;
    private Integer send_address_id;
    private String recv_address;
    private Double weight;
    private String express_id;
    private Double amount;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date add_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date edit_date;
    private String remark;
    private Integer isEnable;
    private Integer isDelete;

    public ExpressOrders() {
    }

    public ExpressOrders(Integer order_id, Integer user_id, Integer send_address_id, String recv_address, String express_id, Double amount, Integer status, Date add_date, Date edit_date, String remark, Integer isEnable, Integer isDelete) {

        this.order_id = order_id;
        this.user_id = user_id;
        this.send_address_id = send_address_id;
        this.recv_address = recv_address;
        this.express_id = express_id;
        this.amount = amount;
        this.status = status;
        this.add_date = add_date;
        this.edit_date = edit_date;
        this.remark = remark;
        this.isEnable = isEnable;
        this.isDelete = isDelete;
    }
}
