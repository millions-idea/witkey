/***
 * @pName proback
 * @name ExpressOrdersController
 * @user HongWei
 * @date 2018/8/4
 * @desc
 */
package com.wanhao.proback.controller.member;

import com.wanhao.proback.bean.member.ExpressOrders;
import com.wanhao.proback.bean.member.ExpressOrdersParam;
import com.wanhao.proback.bean.member.ExpressOrdersView;
import com.wanhao.proback.bean.member.MerchantExpressOrdersParam;
import com.wanhao.proback.bean.util.JsonArrayResult;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.facade.member.express.ExpressOrderFacade;
import com.wanhao.proback.service.member.ExpressOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/express-orders")
public class ExpressOrdersController {

    @Autowired
    private ExpressOrdersService expressOrdersService;

    @Autowired
    private ExpressOrderFacade expressOrderFacade;

    /**
     * 查询快递商品集合 韦德 2018年8月2日23:42:29
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public JsonArrayResult<ExpressOrdersView> getOrdersList(Integer page, String  limit, @RequestParam(required = false) String condition){
        List<ExpressOrdersView> list = expressOrdersService.getOrdersLimit(page, limit, condition);
        if (condition == null || condition.isEmpty()){
            int count = expressOrdersService.getOrdersCount();
            JsonArrayResult jsonArrayResult = new JsonArrayResult(0, list);
            jsonArrayResult.setCount(count);
            return jsonArrayResult;
        }
        return new JsonArrayResult(0, list);
    }

    /**
     * 预览 韦德 2018年8月4日10:24:47
     * @return
     */
    @GetMapping("/view")
    public String view(ExpressOrdersView param, final Model model){
        Utility.formatViewFields(param, model);
        model.addAttribute("model", param);
        if (param.getEdit_date() != null) {
            model.addAttribute("edit_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(param.getEdit_date()));
        }
        return "v2/express/orders/view";
    }

    /**
     * 编辑视图 韦德 2018年8月4日10:24:47
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/editView")
    public String editView(ExpressOrdersView param, final Model model){
        Utility.formatViewFields(param, model);
        model.addAttribute("model", param);
        if (param.getEdit_date() != null) {
            model.addAttribute("edit_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(param.getEdit_date()));
        }
        return "v2/express/orders/edit";
    }

    /**
     * 编辑 韦德 2018年8月4日10:24:47
     * @param param
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public JsonResult edit(ExpressOrders param){
        expressOrdersService.update(param);
        return new JsonResult(0);
    }

    /**
     * 添加 韦德 2018年8月4日10:24:47
     * @return
     */
    @GetMapping("/addView")
    public String addOrdersView(){
        return "v2/express/orders/add";
    }

    /**
     * 添加 韦德 2018年8月4日10:24:47
     * @param param
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public JsonResult addOrders(ExpressOrders param){
        expressOrdersService.add(param);
        return new JsonResult(0);
    }

    /**
     * 删除(支持多个) 韦德 2018年8月4日10:24:47
     * @param id
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public JsonResult deleteOrders(String id){
        expressOrdersService.deleteBy(id);
        return new JsonResult(0);
    }


    /**
     * 编辑运单号 韦德 2018年8月4日23:24:12
     * @param param
     * @return
     */
    @PostMapping("/editExpressId")
    @ResponseBody
    public JsonResult editExpressId(ExpressOrders param){
        expressOrdersService.updateExpressId(param);
        return new JsonResult(0);
    }

    /**
     * 一键发货 韦德 2018年8月5日00:14:10
     * @param param
     * @return
     */
    @PostMapping("/sendOut")
    @ResponseBody
    public JsonResult sendOut(ExpressOrders param){
        expressOrderFacade.placeOrder(param);
        return new JsonResult(0);
    }

    /**
     * 批量下单 韦德 2018年8月5日00:14:10
     * @param expressOrdersParams
     * @return
     */
    @PostMapping("/batchSendOut")
    @ResponseBody
    public JsonResult batchSendOut(@RequestBody List<ExpressOrdersParam> expressOrdersParams){
        expressOrderFacade.placeOrder(expressOrdersParams);
        return new JsonResult(0);
    }

    /**
     * 添加商家代发快递订单 韦德 2018年8月8日00:47:59
     * @param merchantExpressOrdersParam
     * @return
     */
    @PostMapping("/web/add")
    @ResponseBody
    public JsonResult addMerchantOrder(MerchantExpressOrdersParam merchantExpressOrdersParam){
        expressOrdersService.addMerchantOrder(merchantExpressOrdersParam);
        return new JsonResult(0);
    }

    static class Utility {
        /**
         * 格式化视图字段
         * @param param
         * @param model
         */
        public static void formatViewFields(ExpressOrdersView param, Model model) {
            param.setAmount(Double.valueOf(String.format("%.2f",param.getAmount())));
            String status = "未知";
            switch (param.getStatus()){
                case 0:
                    status = "待发货";
                    break;
                case 1:
                    status = "已发货";
                    break;
                case 2:
                    status = "已拒绝";
                    break;
                case 3:
                    status = "已取消";
                    break;
            }
            param.setStatus_text(status);
        }
    }
}
