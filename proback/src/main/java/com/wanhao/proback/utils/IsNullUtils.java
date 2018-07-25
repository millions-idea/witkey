package com.wanhao.proback.utils;

import org.apache.commons.lang.StringUtils;

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
     * @return 为空返回 true 为空返回 false
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
}
