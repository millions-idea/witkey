package com.wanhao.proback.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LiuLiHao on 2018/7/20 15:55.
 * 描述： 系统管理
 * 作者： LiuLiHao
 */
@RequestMapping(value = "system")
@Controller
public class SystemController {

    private static final String PREFIX = "system/";


    /**
     * 进入提现设置页面
     * @return
     */
    @GetMapping(value = "tixian")
    public String toTiXianSetting(){
        return PREFIX+ "tixian-setting";
    }

    /**
     * 修改提现设置
     * @return
     */
    @PostMapping(value = "tixian")
    public String modTiXian(Model model){
        //设置保存到数据库

        //保存成功 message 设置为: 保存成功
        model.addAttribute("message","保存成功");

        return PREFIX+ "tixian-setting";
    }
}
