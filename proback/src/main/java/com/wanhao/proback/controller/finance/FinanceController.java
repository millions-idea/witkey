/***
 * @pName proback
 * @name FinanceController
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.controller.finance;

import com.wanhao.proback.bean.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 财务模块
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {
    @GetMapping
    @ResponseBody
    public JsonResult index(){
        return new JsonResult(0);
    }
}
