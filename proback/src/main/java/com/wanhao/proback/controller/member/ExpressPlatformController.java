/***
 * @pName proback
 * @name ExpressPlatformController
 * @user HongWei
 * @date 2018/8/1
 * @desc 快递空包管理控制器
 */
package com.wanhao.proback.controller.member;

import com.wanhao.proback.bean.member.*;
import com.wanhao.proback.bean.util.JsonArrayResult;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.service.member.ExpressGoodsService;
import com.wanhao.proback.service.member.ExpressPlatformService;
import com.wanhao.proback.service.member.ExpressPostalAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 快递空包管理控制器 韦德 2018年8月1日22:09:03
 */
@Controller
@RequestMapping("/express-platform")
public class ExpressPlatformController {
    @Autowired
    private ExpressPlatformService expressPlatformService;

    @Autowired
    private ExpressGoodsService expressGoodsService;

    @Autowired
    private ExpressPostalAddressService expressPostalAddressService;


    /**
     * 快递空包服务管理-首页 韦德 2018年8月1日22:10:41
     * @return
     */
    @GetMapping
    public String index(){
        return "v2/express/index";
    }

    /**
     * 添加快递空包平台-返回视图 韦德 2018年8月1日22:11:09
     * @return
     */
    @GetMapping("/addView")
    public String addView(){
        return "v2/express/add";
    }

    /**
     * 添加快递空包平台 韦德 2018年8月1日22:11:19
     * @param param
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public JsonResult add(ExpressPlatform param) {
        expressPlatformService.add(param);
        return new JsonResult(0);
    }

    /**
     * 预览快递空包平台信息 韦德 2018年8月1日22:11:31
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/view")
    public String view(ExpressPlatform param, final Model model){
        model.addAttribute("model", param);
        return "v2/express/view";
    }

    /**
     * 编辑页视图 韦德 2018年8月1日22:11:31
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/editView")
    public String editView(ExpressPlatform param, final Model model){
        model.addAttribute("model", param);
        return "v2/express/edit";
    }

    /**
     * 编辑快递空包平台 韦德 2018年8月1日22:11:19
     * @param param
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public JsonResult edit(ExpressPlatform param){
        expressPlatformService.update(param);
        return new JsonResult(0);
    }

    /**
     * 删除快递空包平台(支持多个) 韦德 2018年8月1日23:09:44
     * @param exp_id
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public JsonResult delete(String id){
        expressPlatformService.delete(id);
        return new JsonResult(0);
    }

    /**
     * 查询快递空包集合 韦德 2018年8月1日23:09:44
     * @return
     */
    @GetMapping("/getLimit")
    @ResponseBody
    public JsonArrayResult<ExpressPlatform> getPlatformListLimit(Integer page, String  limit, String condition){
        List<ExpressPlatform> list = expressPlatformService.getPlatformsLimit(page, limit, condition);
        if (condition == null || condition.isEmpty()){
            int count = expressPlatformService.getPlatformCount();
            JsonArrayResult jsonArrayResult = new JsonArrayResult(0, list);
            jsonArrayResult.setCount(count);
            return jsonArrayResult;
        }
        return new JsonArrayResult(0, list);
    }

    /**
     * 查询快递空包集合 韦德 2018年8月1日23:09:44
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public JsonArrayResult<ExpressPlatform> getPlatformList(){
        List<ExpressPlatform> list = expressPlatformService.getPlatforms();
        return new JsonArrayResult(0, list);
    }

    /**
     * 查询快递商品集合 韦德 2018年8月2日23:42:29
     * @return
     */
    @GetMapping("/getGoods")
    @ResponseBody
    public JsonArrayResult<ExpressGoodsView> getGoodsList(Integer page, String  limit, String condition){
        List<ExpressGoodsView> list = expressGoodsService.getGoodsLimit(page, limit, condition);
        if (condition == null || condition.isEmpty()){
            int count = expressGoodsService.getGoodsCount();
            JsonArrayResult jsonArrayResult = new JsonArrayResult(0, list);
            jsonArrayResult.setCount(count);
            return jsonArrayResult;
        }
        return new JsonArrayResult(0, list);
    }

    /**
     * 预览商品信息 韦德 2018年8月3日10:25:14
     * @return
     */
    @GetMapping("/goodsView")
    public String goodsView(ExpressGoodsView param, final Model model){
        param.setDiff_price(Double.valueOf(String.format("%.2f",param.getDiff_price())));
        model.addAttribute("model", param);
        return "v2/express/goods/view";
    }

    /**
     * 商品编辑视图 韦德 2018年8月1日22:11:31
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/goodsEditView")
    public String goodsEditView(ExpressGoodsView param, final Model model){
        param.setDiff_price(Double.valueOf(String.format("%.2f",param.getDiff_price())));
        model.addAttribute("model", param);
        return "v2/express/goods/edit";
    }

    /**
     * 编辑商品 韦德 2018年8月3日15:49:11
     * @param param
     * @return
     */
    @PostMapping("/editGoods")
    @ResponseBody
    public JsonResult editGoods(ExpressGoods param){
        expressGoodsService.update(param);
        return new JsonResult(0);
    }

    /**
     * 添加商品 韦德 2018年8月3日16:17:44
     * @return
     */
    @GetMapping("/addGoodsView")
    public String addGoodsView(){
        return "v2/express/goods/add";
    }

    /**
     * 添加商品 韦德 2018年8月3日17:14:33
     * @param param
     * @return
     */
    @PostMapping("/addGoods")
    @ResponseBody
    public JsonResult addGoods(ExpressGoods param){
        expressGoodsService.add(param);
        return new JsonResult(0);
    }

    /**
     * 删除商品(支持多个) 韦德 2018年8月3日21:48:01
     * @param id
     * @return
     */
    @GetMapping("/deleteGoods")
    @ResponseBody
    public JsonResult deleteGoods(String id){
        expressGoodsService.deleteBy(id);
        return new JsonResult(0);
    }


    /**
     * 获取发货地址列表 韦德 2018年8月7日22:49:14
     * @param userId
     * @return
     */
    @GetMapping("/web/getPostalAddresses")
    @ResponseBody
    public JsonArrayResult<ExpressPostalAddress> getPostalAddresses(Integer userId){
        List<ExpressPostalAddress> list = expressPostalAddressService.getPostalAddresses(userId);
        return new JsonArrayResult(0, list);
    }


    /**
     * 获取快递分类 韦德 2018年8月7日23:42:19
     * @return
     */
    @GetMapping("/web/getExpressCategory")
    @ResponseBody
    public JsonArrayResult<ExpressGoodsJsonView> getExpressCategory(){
        List<ExpressGoodsJsonView> list = expressGoodsService.getGoods();
        return new JsonArrayResult(0, list);
    }

}
