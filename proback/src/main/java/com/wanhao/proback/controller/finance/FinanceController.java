/***
 * @pName proback
 * @name FinanceController
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.controller.finance;

import com.wanhao.proback.bean.finance.TransactionsView;
import com.wanhao.proback.bean.util.JsonArrayResult;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.service.finance.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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

    /**
     * 交易清单
     * @return
     */
    @GetMapping("/statement")
    public String  statement(){
        return "v2/finance/statement/index";
    }


    /**
     * 根据查询条件获取品牌电商公司数据 韦德 2018年8月3日11:48:50
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
}
