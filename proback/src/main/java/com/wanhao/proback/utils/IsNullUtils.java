package com.wanhao.proback.utils;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiuLiHao on 2018/7/21 11:48.
 * 描述： 判断字段是否为空
 * 作者： LiuLiHao
 */

public class IsNullUtils {

    /**
     * 判空
     * @return 为空返回 true 为空返回 false
     */
    public static boolean isContainsNull(String... arr){
        if (arr!=null && arr.length>0){
            for (int i=0;i<arr.length;i++){
                String s = arr[i];
                if (StringUtils.isBlank(s)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判空
     * @return 为空返回 true
     *         非空返回 false
     */
    public static boolean isNull(Object... arr){
        if (arr!=null && arr.length>0){
            for (int i=0;i<arr.length;i++){
                Object o = arr[i];

                if (o instanceof String && StringUtils.isBlank((String) o)){
                    return true;
                }else if (o==null){
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * 判空
     * @return 为空返回 true
     *         非空返回 false
     */
    public static boolean isNullWithResponse(HttpServletResponse response,Object... arr){
        boolean flag = false;
        if (arr!=null && arr.length>0){
            for (int i=0;i<arr.length;i++){
                Object o = arr[i];

                if (o instanceof String && StringUtils.isBlank((String) o)){
                    flag =  true;
                    break;
                }else if (o==null){
                    flag =  true;
                    break;
                }

            }
        }

        if (flag){
            JsonObject jsonObject = new JsonObject();
            ResponseUtils.retnFailMsg(response,jsonObject);
            return flag;
        }

        return false;
    }
}