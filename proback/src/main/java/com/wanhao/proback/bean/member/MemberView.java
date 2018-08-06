/***
 * @pName proback
 * @name MemberView
 * @user HongWei
 * @date 2018/8/6
 * @desc
 */
package com.wanhao.proback.bean.member;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 会员数据表视图
 */
@Setter
@Getter
@ToString
public class MemberView extends Member {

    /**
     * 系统公户专用视图接口
     */
    public interface SystemAccountView{};

    @JsonView(SystemAccountView.class)
    /**
     * 账户名
     */
    private String username;

    @JsonView(SystemAccountView.class)
    /**
     * 账户余额
     */
    private Double balance;

    @JsonView(SystemAccountView.class)
    /**
     * 总收入
     */
    private Double incomeAmount;


    @JsonView(SystemAccountView.class)
    /**
     * 总支出
     */
    private Double expendAmount;
}
