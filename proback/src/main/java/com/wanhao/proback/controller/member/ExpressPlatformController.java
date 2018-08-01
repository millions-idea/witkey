/***
 * @pName proback
 * @name ExpressPlatformController
 * @user HongWei
 * @date 2018/8/1
 * @desc
 */
package com.wanhao.proback.controller.member;

import com.wanhao.proback.service.member.ExpressPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/express-platform")
public class ExpressPlatformController {
    @Autowired
    private ExpressPlatformService expressPlatformService;

    @GetMapping
    public String index(){
        return "";
    }
}
