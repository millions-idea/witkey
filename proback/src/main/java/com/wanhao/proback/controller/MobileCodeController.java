package com.wanhao.proback.controller;

import com.google.gson.JsonObject;
import com.wanhao.proback.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * Created by LiuLiHao on 2018/7/19 10:18.
 * 描述： 短信验证操作
 * 作者： LiuLiHao
 */
@Controller
@RequestMapping(value = "mobileCode")
public class MobileCodeController {
    //先使用随机数作为验证码
    private static Random random = new Random();

    public static final String MOBILE_CODE = "mobile_code";


    @PostMapping(value = "sendSms")
    public void sendSms(@RequestBody String mobile, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        System.out.println("短信验证码:"+sb.toString());

        //存入
        session.setAttribute(MOBILE_CODE,sb.toString());
        //返回数据
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success","1");
        ResponseUtils.renderJson(response,jsonObject.toString());
    }


    /**
     * 检查验证码是否正确
     *
     * @param req
     * @param resp
     * @return
     */
    @PostMapping(value = "checkMobileCode")
    public void checkVerifyCode(@RequestParam(value = "mobileCode") String mobileCode,
                                HttpServletRequest req, HttpServletResponse resp,
                                HttpSession session) {
        JsonObject jsonObject = new JsonObject();

        if(mobileCode!=null){
            String validateCode= (String) session.getAttribute(MOBILE_CODE);
            if(validateCode!= null && validateCode.equals(mobileCode)){
                jsonObject.addProperty("error", "0");
            }else{
                jsonObject.addProperty("error", "1");
            }
        }else{
            jsonObject.addProperty("error", "1");
        }
        ResponseUtils.renderJson(resp,jsonObject.toString());
    }

}
