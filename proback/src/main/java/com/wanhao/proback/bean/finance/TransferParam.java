/***
 * @pName proback
 * @name TransferParam
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.bean.finance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 转账
 */
@Setter
@Getter
public class TransferParam {
    private Integer formUid;
    private Integer toUid;
    private Double amount;
    private String remark;

    public TransferParam() {
    }

    public TransferParam(Integer formUid, Integer toUid, Double amount, String remark) {

        this.formUid = formUid;
        this.toUid = toUid;
        this.amount = amount;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "formUid=" + formUid +
                "&toUid=" + toUid +
                "&amount=" + amount +
                "&remark='" + remark;
    }
}
