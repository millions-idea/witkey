/***
 * @pName proback
 * @name FinanceController
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.controller.finance;

import com.wanhao.proback.annotaion.FinanceToken;
import com.wanhao.proback.bean.finance.TransferParam;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.exception.FinanceException;
import com.wanhao.proback.service.finance.PayService;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 财务交易模块
 */
@Controller
@RequestMapping("/finance/pay")
public class PayController {
    @Autowired
    private PayService payService;

    @Autowired
    private MemberService memberService;

    @FinanceToken
    @PostMapping("/transfer")
    @ResponseBody
    public JsonResult transfer(TransferParam transferParam){
        payService.transfer(transferParam);
        return new JsonResult(0);
    }


    @GetMapping("/recharge")
    /**
     * 手工充值页面
     * @return
     */
    public String rechargeView(String recordId){
        return "v2/finance/pay/recharge";
    }


    @FinanceToken
    @GetMapping("/doRecharge")
    @ResponseBody
    /**
     * 手工充值
     * @return
     */
    public JsonResult recharge(String username, Double amount) throws Exception {
        Member member = memberService.getMemberByUserName(username);
        if(member == null) throw new FinanceException(FinanceException.Errors.NOT_FOUND_USER,"用户不存在");

        if(payService.recharge(member.getId(), amount)) return new JsonResult(0);
        return new JsonResult(1);
    }

}
