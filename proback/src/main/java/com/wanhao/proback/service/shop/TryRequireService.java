package com.wanhao.proback.service.shop;

import com.wanhao.proback.bean.shop.TryRequire;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/24 16:20.
 * 描述：
 * 作者： LiuLiHao
 */
public interface TryRequireService {

    List<TryRequire> getGoods(TryRequire require);


    void update(TryRequire require);

    void add(TryRequire require);

    void delete(int id);

}
