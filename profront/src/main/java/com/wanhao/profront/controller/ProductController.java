/***
 * @pName profront
 * @name ProductController
 * @user HongWei
 * @date 2018/8/10
 * @desc
 */
package com.wanhao.profront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String index(){
        return "v2/products/index";
    }
}
