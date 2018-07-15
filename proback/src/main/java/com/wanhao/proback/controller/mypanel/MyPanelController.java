package com.wanhao.proback.controller.mypanel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by LiuLiHao on 2018/7/15 19:22.
 * 描述： 我的面板
 * 作者： LiuLiHao
 */
@Controller
@RequestMapping(value = "myPanel")
public class MyPanelController {

    /**
     * 系统首页
     * @return
     */
    @RequestMapping(value = "systemIndex")
    public String systemIndex(Map<String,Object> map){
        return "mypanel/system-index";
    }
}
