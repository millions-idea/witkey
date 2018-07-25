package com.wanhao.proback.service;

/**
 * Created by LiuLiHao on 2018/7/25 15:54.
 * 描述： service
 * 作者： LiuLiHao
 */
public interface BaseService<T> {


    void update(T v);

    void add(T v);

    void delete(Integer id);


}
