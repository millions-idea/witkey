package com.wanhao.proback.controller.intf.setting;

import com.google.gson.JsonObject;
import com.wanhao.proback.bean.Setting;
import com.wanhao.proback.service.SettingService;
import com.wanhao.proback.utils.GsonUtils;
import com.wanhao.proback.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiuLiHao on 2018/7/22 17:05.
 * 描述：
 * 作者： LiuLiHao
 */
@Controller(value = "intf_setting")
@RequestMapping(value = "intf_setting")
public class SettingController {

    @Autowired
    SettingService settingService;

    /**
     * 获取配置
     */
    @RequestMapping(value = "getSetting")
    public void getSetting(HttpServletRequest request, HttpServletResponse response){
        Setting setting = settingService.query();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("setting",GsonUtils.toJson(setting));
        ResponseUtils.retnSuccessMsg(response,jsonObject);
    }


}
