package com.wanhao.proback.service.shop;

import com.wanhao.proback.bean.shop.BuyerRequire;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/24 16:21.
 * 描述：
 * 作者： LiuLiHao
 */
public interface BuyerRequireService {

    List<BuyerRequire> getGoods(BuyerRequire require);


    void update(BuyerRequire require);

    void add(BuyerRequire require);

    void delete(int id);
}
