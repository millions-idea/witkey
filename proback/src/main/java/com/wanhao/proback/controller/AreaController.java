package com.wanhao.proback.controller;

import com.google.gson.Gson;
import com.wanhao.proback.bean.Area;
import com.wanhao.proback.service.AreaService;
import com.wanhao.proback.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/20 20:38.
 * 描述：
 * 作者： LiuLiHao
 */
@RequestMapping(value = "area")
@Controller
public class AreaController {

    @Autowired
    AreaService areaService;

    @RequestMapping(value = "getAllProvince")
    @ResponseBody
    public void getAllProvince(HttpServletResponse response){
        //查询所有省份
        List<Area> list = areaService.getAllProvince();
        Gson gson = new Gson();
        String s = gson.toJson(list);
        ResponseUtils.renderJson(response,s);
    }

    @RequestMapping(value = "getCity")
    @ResponseBody
    public void getAllProvince(Integer cid, HttpServletResponse response){
        //查询所有市
        List<Area> list = areaService.getAllCity(cid);
        Gson gson = new Gson();
        String s = gson.toJson(list);
        ResponseUtils.renderJson(response,s);
    }
}

