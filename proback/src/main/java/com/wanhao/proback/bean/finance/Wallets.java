/***
 * @pName proback
 * @name Wallets
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.bean.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;

/**
 * 财务钱包表
 */
@Table(name = "tb_wallets")
@Setter
@Getter
@ToString
public class Wallets {
    private Integer wallet_id;
    private Integer user_id;
    private Double balance;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date edit_date;
    private Integer version;

    public Wallets() {
    }

    public Wallets(Integer wallet_id, Integer user_id, Double balance, Date edit_date, Integer version) {

        this.wallet_id = wallet_id;
        this.user_id = user_id;
        this.balance = balance;
        this.edit_date = edit_date;
        this.version = version;
    }
}
