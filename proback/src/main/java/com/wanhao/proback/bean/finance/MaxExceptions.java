/***
 * @pName proback
 * @name MaxExceptions
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
 * 重要异常日志表
 */
@Table(name = "tb_max_exceptions")
@Setter
@Getter
@ToString
public class MaxExceptions {
    private Integer log_id;
    private Integer user_id;
    private String  username;
    private String body;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date add_date;

    public MaxExceptions() {
    }

    public MaxExceptions(Integer user_id, String body) {

        this.user_id = user_id;
        this.body = body;
    }
}
