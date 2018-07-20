package com.wanhao.proback.service;

import com.wanhao.proback.bean.Area;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/16 14:07.
 * 描述：
 * 作者： LiuLiHao
 */
public interface AreaService {
    /**
     * 查询所有省
     * @return
     */
    List<Area> getAllProvince();


    /**
     * 查询所有市
     * @return
     */
    List<Area> getAllCity(Integer cid);

}
