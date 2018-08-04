package com.wanhao.proback.controller.system;

import com.wanhao.proback.bean.Setting;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.service.SettingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by LiuLiHao on 2018/7/20 15:55.
 * 描述： 系统管理
 * 作者： LiuLiHao
 */
@RequestMapping(value = "system")
@Controller
public class SystemController {

    private static final String PREFIX = "v2/setting/tixian/";


    @Autowired
    SettingService settingService;

    /**
     * 进入提现设置页面
     * @return
     */
    @GetMapping(value = "tixian")
    public String toTiXianSetting(Model model){
        Setting setting = settingService.query();
        //放入前台
        model.addAttribute("setting",setting);

        return PREFIX+ "index";
    }

    /**
     * 修改提现设置
     * @return
     */
    @PostMapping(value = "tixian")
    public String modTiXian(Integer open_tixian,Integer tixian_count,
                            Double min_money,Double max_money,
                            Double shouxu,Double min_shouxu,
                            Double max_shouxu,
                            Model model){
        //设置保存到数据库
        Setting setting = new Setting();
        setting.setOpen_tixian(open_tixian);
        setting.setTixian_count(tixian_count);
        setting.setMin_money(min_money);
        setting.setMax_money(max_money);
        setting.setShouxu(shouxu);
        setting.setMin_shouxu(min_shouxu);
        setting.setMax_shouxu(max_shouxu);

        settingService.update(setting);

        //保存成功 message 设置为: 保存成功
        model.addAttribute("message","保存成功");
        model.addAttribute("setting",setting);


        return PREFIX+ "tixian-setting";
    }

    /**
     * 编辑视图
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/editView")
    public String editView(Setting param, final Model model){
        model.addAttribute("model", param);
        return PREFIX+"edit";
    }

    /**
     * 提交编辑提现设置
     * @param param
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public JsonResult edit(Setting param){
        settingService.update(param);
        return new JsonResult(0);
    }


    /**
     * 网站设置
     * @return
     */
    @GetMapping(value = "webSetting")
    public String webSetting(Model model){
        Setting setting = settingService.query();
        model.addAttribute("setting",setting);

        return PREFIX + "web-setting";
    }

    /**
     * 修改设置
     * @return
     */
    @PostMapping(value = "webSetting")
    public String modSetting(Setting setting,Model model,
                             String web_logo_mod,String mobile_logo_mod,
                             String app_logo_mod,String goods_default_img_mod){
        //设置修改后的图片
        if (StringUtils.isNotBlank(web_logo_mod)){
            setting.setWeb_logo(web_logo_mod);
        }
        if (StringUtils.isNotBlank(mobile_logo_mod)){
            setting.setMobile_logo(mobile_logo_mod);
        }
        if (StringUtils.isNotBlank(app_logo_mod)){
            setting.setApp_logo(app_logo_mod);
        }

        if (StringUtils.isNotBlank(goods_default_img_mod)){
            setting.setApp_logo(goods_default_img_mod);
        }

        //保存修改
        settingService.update(setting);
        Setting query = settingService.query();
        model.addAttribute("setting",query);
        return PREFIX + "web-setting";

    }

}
