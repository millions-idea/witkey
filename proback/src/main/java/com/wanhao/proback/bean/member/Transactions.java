/***
 * @pName proback
 * @name Transcations
 * @user HongWei
 * @date 2018/8/5
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
 * 交易流水表
 */
@Table(name = "tb_transactions")
@Setter
@Getter
@ToString
public class Transactions {
    private Integer transaction_id;
    private String record_id;
    private String record_no;
    private Integer from_uid;
    private Integer to_uid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trade_date;
    private Integer trade_type;
    private Double trade_amount;
    private String remark;

    public Transactions() {
    }

    public Transactions(Integer transaction_id, String record_id, String record_no, Integer from_uid, Integer to_uid, Date trade_date, Integer trade_type, Double trade_amount, String remark) {

        this.transaction_id = transaction_id;
        this.record_id = record_id;
        this.record_no = record_no;
        this.from_uid = from_uid;
        this.to_uid = to_uid;
        this.trade_date = trade_date;
        this.trade_type = trade_type;
        this.trade_amount = trade_amount;
        this.remark = remark;
    }
}
