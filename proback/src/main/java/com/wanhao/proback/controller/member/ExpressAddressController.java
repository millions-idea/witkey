/***
 * @pName proback
 * @name ExpressAddressController
 * @user HongWei
 * @date 2018/8/9
 * @desc
 */
package com.wanhao.proback.controller.member;

import com.wanhao.proback.annotaion.ISLogin;
import com.wanhao.proback.bean.member.ExpressGoods;
import com.wanhao.proback.bean.member.ExpressPostalAddress;
import com.wanhao.proback.bean.util.JsonArrayResult;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.service.member.ExpressPostalAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/express-address")
public class ExpressAddressController {

    @Autowired
    private ExpressPostalAddressService expressPostalAddressService;

    /**
     * 获取发货地址列表 韦德 2018年8月7日22:49:14
     * @param userId
     * @return
     */
    @ISLogin
    @GetMapping("/web/getPostalAddresses")
    @ResponseBody
    public JsonArrayResult<ExpressPostalAddress> getPostalAddresses(Integer userId){
        List<ExpressPostalAddress> list = expressPostalAddressService.getPostalAddresses(userId);
        return new JsonArrayResult(0, list);
    }

    /**
     * 添加发货地址 韦德 2018年8月3日17:14:33
     * @param param
     * @return
     */
    @PostMapping("/web/addExpressAddress")
    @ResponseBody
    public JsonResult addExpressAddress(ExpressPostalAddress param){
        expressPostalAddressService.addExpressAddress(param);
        return new JsonResult(0);
    }

    /**
     * 编辑发货地址 韦德 2018年8月3日17:14:33
     * @param param
     * @return
     */
    @PostMapping("/web/editExpressAddress")
    @ResponseBody
    public JsonResult editExpressAddress(ExpressPostalAddress param){
        expressPostalAddressService.update(param);
        return new JsonResult(0);
    }

    /**
     * 删除发货地址 韦德 2018年8月3日17:14:33
     * @param id
     * @return
     */
    @GetMapping("/web/deleteExpressAddress")
    @ResponseBody
    public JsonResult deleteExpressAddress(Integer id){
        expressPostalAddressService.delete(id);
        return new JsonResult(0);
    }

}
