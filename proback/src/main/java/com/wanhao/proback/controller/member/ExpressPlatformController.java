/***
 * @pName proback
 * @name ExpressPlatformController
 * @user HongWei
 * @date 2018/8/1
 * @desc
 */
package com.wanhao.proback.controller.member;

import com.wanhao.proback.annotaion.ISLogin;
import com.wanhao.proback.bean.member.ExpressPlatform;
import com.wanhao.proback.service.member.ExpressPlatformService;
import com.wanhao.proback.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/express-platform")
public class ExpressPlatformController {
    @Autowired
    private ExpressPlatformService expressPlatformService;

    @GetMapping
    public String index(){
        return "v2/express/index";
    }

    @GetMapping("/addView")
    public String addView(){
        return "v2/express/add";
    }

    @PostMapping("/add")
    public String add(ExpressPlatform param){
        expressPlatformService.add(param);
        return "v2/express/index";
    }
}
