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
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.service.finance.PayService;
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

    @FinanceToken
    @PostMapping("/transfer")
    @ResponseBody
    public JsonResult transfer(TransferParam transferParam){
        payService.transfer(transferParam);
        return new JsonResult(1);
    }
}
