package com.wanhao.proback.bean.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by LiuLiHao on 2018/6/17 19:59.
 * 描述：
 * 作者： LiuLiHao
 */
@Setter
@Getter
@ToString
public class IpResult implements Serializable {

    /**
     * status : 1
     * info : OK
     * infocode : 10000
     * province : 北京市
     * city : 北京市
     * adcode : 110000
     * rectangle : 116.0119343,39.66127144;116.7829835,40.2164962
     */

    private String status;
    private String info;
    private String infocode;
    private String province;
    private String city;
    private String adcode;
    private String rectangle;

}
