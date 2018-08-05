/***
 * @pName proback
 * @name RequestUtil
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/***
 * 网页请求工具类
 */
public class RequestUtil {
    /**
     * 获取url风格的参数
     * @param request
     * @return
     */
    public static String getParameters(HttpServletRequest request) {
        String url = "";
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            if(name.equals("sign")) continue;;
            String value = request.getParameter(name);
            url += name + "=" + value;
            if(parameterNames.hasMoreElements()){
                url += "&";
            }
        }
        if(url.endsWith("&")){
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }
}
