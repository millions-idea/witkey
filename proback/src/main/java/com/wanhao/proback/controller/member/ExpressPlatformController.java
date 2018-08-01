/***
 * @pName proback
 * @name ExpressPlatformController
 * @user HongWei
 * @date 2018/8/1
 * @desc 快递空包管理控制器
 */
package com.wanhao.proback.controller.member;

import com.wanhao.proback.bean.member.ExpressPlatform;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.service.member.ExpressPlatformService;
import com.wanhao.proback.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 快递空包管理控制器 韦德 2018年8月1日22:09:03
 */
@Controller
@RequestMapping("/express-platform")
public class ExpressPlatformController {
    @Autowired
    private ExpressPlatformService expressPlatformService;

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
    public String add(ExpressPlatform param){
        expressPlatformService.add(param);
        return "redirect:/express-platform";
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
    public String edit(ExpressPlatform param){
        expressPlatformService.update(param);
        return "redirect:/express-platform";
    }

    /**
     * 删除快递空包平台(支持多个) 韦德 2018年8月1日23:09:44
     * @param exp_id
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public JsonResult delete(String exp_id){
        expressPlatformService.delete(exp_id);
        return new JsonResult(0);
    }
}
