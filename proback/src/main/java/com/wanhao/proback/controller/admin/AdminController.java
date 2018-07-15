package com.wanhao.proback.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by LiuLiHao on 2018/7/14 18:51.
 * 描述：管理员操作
 * 作者： LiuLiHao
 */
@RequestMapping(value = "myAdmin")
@Controller
public class AdminController {

    /**
     * 跳转到登录页
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String toLogin(){
        return "admin/login";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam Map<String,Object> map){
        if (map.get("error")!=null){
            map.put("","密码错误");
        }
        System.out.println(map.get("username"));
        System.out.println(map.get("password"));
        return "admin/login";
    }
}
