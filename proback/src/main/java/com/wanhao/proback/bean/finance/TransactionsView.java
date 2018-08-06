/***
 * @pName proback
 * @name TransactionsView
 * @user HongWei
 * @date 2018/8/6
 * @desc
 */
package com.wanhao.proback.bean.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;

/**
 * 交易流水表视图
 */
@Setter
@Getter
@ToString
public class TransactionsView extends Transactions {
    private String from_username;
    private String to_username;
}
