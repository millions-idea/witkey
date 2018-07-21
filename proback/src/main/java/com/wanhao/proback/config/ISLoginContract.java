package com.wanhao.proback.config;

import com.google.gson.JsonObject;
import com.wanhao.proback.annotaion.ISLogin;
import com.wanhao.proback.exception.FormRepeatException;
import com.wanhao.proback.utils.Constants;
import com.wanhao.proback.utils.ResponseUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiuLiHao on 2018/7/21 10:50.
 * 描述： 判断是否已经登录
 * 作者： LiuLiHao
 */
@Aspect
@Component
public class ISLoginContract {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(isLogin)")
    public void testToken(final JoinPoint joinPoint, ISLogin isLogin){
        try {
            if (isLogin != null) {
                //获取 joinPoint 的全部参数
                Object[] args = joinPoint.getArgs();
                HttpServletRequest request = null;
                HttpServletResponse response = null;
                for (int i = 0; i < args.length; i++) {
                    //获得参数中的 request && response
                    if (args[i] instanceof HttpServletRequest) {
                        request = (HttpServletRequest) args[i];
                    }
                    if (args[i] instanceof HttpServletResponse) {
                        response = (HttpServletResponse) args[i];
                    }
                }
                String mobile = request.getParameter("mobile");
                String sendToken = request.getParameter("token");
                //获取不到
                if (mobile!=null){
                    System.out.println(mobile);
                }
                if (sendToken!=null){
                    System.out.println(sendToken);
                }
                String mobile1 = request.getHeader("mobile");
                if (mobile1!=null){
                    System.out.println(mobile1);
                }
                String sendToken2 = request.getHeader("token");
                if (sendToken2!=null){
                    System.out.println(sendToken2);
                }
                //todo 验证是否异地登录
                ServletContext servletContext = request.getServletContext();
                String sToken = (String) servletContext.getAttribute(Constants.TOKEN);
                if (StringUtils.isNotBlank(sToken) &&
                        StringUtils.isNotBlank(sendToken2) &&
                        sendToken2.equals(sToken)){
                    //正确
                }else {
                    //错误
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("error",1);
                    jsonObject.addProperty("message","登录过期");
                    ResponseUtils.renderJson(response,jsonObject.toString());
                }

            }

        } catch (FormRepeatException e){

        } catch (Exception e){

        }
    }

}
