package com.wanhao.profront.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * Created by LiuLiHao on 2018/7/16 18:19.
 * 描述：验证码
 * 作者： LiuLiHao
 */
@RequestMapping(value = "kaptcha")
@Controller
public class KaptchaController {

    @Autowired
    DefaultKaptcha captchaProducer;

    @RequestMapping(value = "/captcha-image")
    public ModelAndView getKaptchaImage(HttpServletRequest request,
                                        HttpServletResponse response,
                                        HttpSession session) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        session.setAttribute("vali","capText");
        System.out.println("capText: " + capText);

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }



        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }
}
