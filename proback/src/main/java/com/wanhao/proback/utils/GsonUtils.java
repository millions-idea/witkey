package com.wanhao.proback.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by LiuLiHao on 2018/7/21 13:38.
 * desc： convert object to json string
 * author： LiuLiHao
 */
public class GsonUtils {

    //singleton mode
    private static final Gson gson = new Gson();
    private static final Gson gsonText = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * 2json
     * @param object
     * @return
     */
    public static String toJson(Object object){
        return gson.toJson(object);
    }

    /**
     * 获取json字符串并忽略Unicode转义
     * @param object
     * @return
     */
    public static String toJsonText(Object object){
        return gsonText.toJson(object);
    }

}
