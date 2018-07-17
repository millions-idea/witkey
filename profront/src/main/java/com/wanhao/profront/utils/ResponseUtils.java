package com.wanhao.profront.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LiuLiHao on 2018/7/17 21:01.
 * 描述： 返回信息
 * 作者： LiuLiHao
 */
public class ResponseUtils {


    /**
     * 发送指定类型数据
     * @param text
     */
    public static void render(HttpServletResponse response, String contentType, String text){
        response.setContentType(contentType);
        try {
            response.getWriter().write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**发送json*/
    public static void renderJson(HttpServletResponse response,String text){
        render(response, "application/json;charset=UTF-8", text);
    }
    /**发送xml*/
    public static void renderXml(HttpServletResponse response,String text){
        render(response, "text/xml;charset=UTF-8", text);
    }
    /**发送text*/
    public static void renderText(HttpServletResponse response,String text){
        render(response, "application/plain;charset=UTF-8", text);
    }

}
