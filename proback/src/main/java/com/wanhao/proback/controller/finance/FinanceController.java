/***
 * @pName proback
 * @name FinanceController
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.controller.finance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 财务模块
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {
    /**
     * 交易清单
     * @return
     */
    @GetMapping("/statement")
    public String  statement(){
        return "v2/finance/statement/index";
    }
}
