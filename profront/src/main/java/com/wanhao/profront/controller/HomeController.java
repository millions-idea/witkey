/***
 * @pName profront
 * @name HomeController
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.profront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(value = {"","/login"})
    public String index(){
        return "v2/home/login";
    }

    @GetMapping("/register")
    public String register(){
        return "v2/home/register";
    }

    @GetMapping("/index-seller")
    public String indexSeller(){
        return "v2/home/register";
    }
}
