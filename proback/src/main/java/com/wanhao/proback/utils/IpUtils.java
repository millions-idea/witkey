package com.wanhao.proback.utils;

import com.google.gson.Gson;
import com.wanhao.proback.bean.util.IpResult;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuLiHao on 2018/6/17 19:34.
 * 描述：
 * 作者： LiuLiHao
 */
public class IpUtils {
    static String AppCode = "e58bee61a879426bbc392a31b27a7a0f";
    private static Gson gson = new Gson();

    /**
     * 接口1  不太好用
     * 根据IP查询地址
     * @param ipAddr
     * @return
     */
    private static String queryIp(String ipAddr){
        String host = "https://dm-81.data.aliyun.com";
        String path = "/rest/160601/ip/getIpInfo.json";
        String method = "GET";
        String appcode = AppCode;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ip", ipAddr);


        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 接口2  使用中
     * ip地址查询
     * @return
     */
    public static String getLocation(String address){
        String host = "http://iploc.market.alicloudapi.com";
        String path = "/v3/ip";
        String method = "GET";
        String appcode = "e58bee61a879426bbc392a31b27a7a0f";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ip", address);

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            String s = EntityUtils.toString(response.getEntity());
            IpResult ipResult = gson.fromJson(s, IpResult.class);
            String location = ipResult.getCity()+ipResult.getProvince();

            return location;
        } catch (Exception e) {

        }
        return null;
    }
}