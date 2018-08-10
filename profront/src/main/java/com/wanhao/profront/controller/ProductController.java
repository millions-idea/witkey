/***
 * @pName profront
 * @name ProductController
 * @user HongWei
 * @date 2018/8/10
 * @desc
 */
package com.wanhao.profront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/task")
public class ProductController {
    @GetMapping
    public String index(){
        return "v2/task/index";
    }
}
