package com.wanhao.proback.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LiuLiHao on 2018/7/14 18:51.
 * 描述：管理员操作
 * 作者： LiuLiHao
 */
@RequestMapping(value = "myAdmin")
public class AdminController {

    @RequestMapping(value = "login")
    public String login(){
        return "admin/login";
    }
}
