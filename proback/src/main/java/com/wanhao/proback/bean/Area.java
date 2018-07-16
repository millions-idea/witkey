package com.wanhao.proback.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by LiuLiHao on 2018/7/16 13:54.
 * 描述：
 * 作者： LiuLiHao
 */
@Setter
@Getter
@ToString
public class Area implements Serializable {
    private Integer codeid;
    private Integer parentid;
    private String cityName;

}
