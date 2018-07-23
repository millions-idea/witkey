package com.wanhao.proback.controller.intf.vip;

import com.wanhao.proback.bean.vip.Vip;
import com.wanhao.proback.service.vip.VipService;
import com.wanhao.proback.utils.GsonUtils;
import com.wanhao.proback.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/23 11:48.
 * 描述：
 * 作者： LiuLiHao
 */
@Controller(value = "intf_vip")
@RequestMapping(value = "intf_vip")
public class VipController {

    @Autowired
    VipService vipService;

    /**
     * vip价格列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "loadList")
    public void loadList(HttpServletRequest request, HttpServletResponse response){
        List<Vip> list = vipService.getAll();
        String s = GsonUtils.toJson(list);

        ResponseUtils.renderJson(response,s);
    }
}
