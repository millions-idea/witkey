/***
 * @pName proback
 * @name FinanceController
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.controller.finance;

import com.fasterxml.jackson.annotation.JsonView;
import com.wanhao.proback.bean.finance.MoneysView;
import com.wanhao.proback.bean.finance.TransactionsView;
import com.wanhao.proback.bean.member.MemberView;
import com.wanhao.proback.bean.util.JsonArrayResult;
import com.wanhao.proback.service.finance.PayService;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * 财务模块
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    private PayService payService;
    @Autowired
    private MemberService memberService;

    /**
     * 交易清单
     * @return
     */
    @GetMapping("/statement")
    public String  statement(){
        return "v2/finance/statement/index";
    }

    /**
     * 记账凭证
     * @return
     */
    @GetMapping("/account-voucher")
    public String  accountBook(String recordId){
        return "v2/finance/account-voucher/index";
    }

    /**
     * 查询流水-分页 韦德 2018年8月3日11:48:50
     * @param condition
     * @return
     */
    @RequestMapping("/statement/getLimit")
    @ResponseBody
    public JsonArrayResult<TransactionsView> getStatements(Integer page, String limit, String condition, Integer trade_type, String trade_date_begin, String trade_date_end){
        List<TransactionsView> list = payService.getTransactionsLimit(page, limit, condition, trade_type, trade_date_begin, trade_date_end);
        if (condition == null || condition.isEmpty()){
            int count = payService.getTransactionsCount();
            JsonArrayResult jsonArrayResult = new JsonArrayResult(0, list);
            jsonArrayResult.setCount(count);
            return jsonArrayResult;
        }
        return new JsonArrayResult(0, list);
    }

    /**
     * 查询系统账户信息
     * @return
     */
    @GetMapping("/getSystemAccount")
    @JsonView(MemberView.SystemAccountView.class)
    @ResponseBody
    public MemberView getSystemAccount(){
        MemberView memberView = memberService.getMemberById(Constants.SYSTEM_ACCOUNT);
        if(memberView != null) {
            MemberView accountAmount = payService.getAccountAmount(Constants.SYSTEM_ACCOUNT);
            if(accountAmount != null) {
                memberView.setIncomeAmount(accountAmount.getIncomeAmount());
                memberView.setExpendAmount(accountAmount.getExpendAmount());
            }
        }
        return memberView;
    }

    /**
     * 查询凭证-分页 韦德 2018年8月3日11:48:50
     * @param condition
     * @return
     */
    @RequestMapping("/account-voucher/getLimit")
    @ResponseBody
    public JsonArrayResult<MoneysView> getVouchers(Integer page, String limit, String condition, Integer trade_type, String trade_date_begin, String trade_date_end){
        List<MoneysView> list = payService.getMoneysLimit(page, limit, condition, trade_type, trade_date_begin, trade_date_end);
        if (condition == null || condition.isEmpty()){
            int count = payService.getMoneysCount();
            JsonArrayResult jsonArrayResult = new JsonArrayResult(0, list);
            jsonArrayResult.setCount(count);
            return jsonArrayResult;
        }
        return new JsonArrayResult(0, list);
    }

}
