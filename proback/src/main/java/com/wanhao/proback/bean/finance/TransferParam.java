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

/**
 * 转账
 */
@Setter
@Getter
public class TransferParam {
    private Integer fromUid;
    private Integer toUid;
    private Double amount;
    private String remark;
    private Integer tradeType;
    private String recordNo;

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public TransferParam() {

    }

    public TransferParam(Integer fromUid, Integer toUid, Double amount, String remark, Integer tradeType, String recordNo) {

        this.fromUid = fromUid;
        this.toUid = toUid;
        this.amount = amount;
        this.remark = remark;
        this.tradeType = tradeType;
        this.recordNo = recordNo;
    }

    @Override
    public String toString() {
        return "fromUid=" + fromUid +
                "&toUid=" + toUid +
                "&amount=" + amount +
                "&remark=" + remark +
                "&tradeType='" + tradeType;
    }
}
