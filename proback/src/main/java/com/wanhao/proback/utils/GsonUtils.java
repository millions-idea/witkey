package com.wanhao.proback.utils;

import com.google.gson.Gson;

/**
 * Created by LiuLiHao on 2018/7/21 13:38.
 * 描述：
 * 作者： LiuLiHao
 */
public class GsonUtils {

    /**
     * 转json
     * @param object
     * @return
     */
    public static String toJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

}
