/***
 * @pName proback
 * @name Moneys
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
 * 资金变化表
 */
@Table(name = "tb_moneys")
@Setter
@Getter
@ToString
public class Moneys {
    private Integer log_id;
    private String record_id;
    private Integer from_uid;
    private Integer trade_type;
    private Double trade_amount;
    private Double account_balance;
    private String remark;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date add_date;
}
