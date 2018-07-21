package com.wanhao.proback.service.shop;

import com.wanhao.proback.bean.shop.Shop;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/21 14:48.
 * 描述：
 * 作者： LiuLiHao
 */
public interface ShopService {

    void add(Shop shop);

    void update(Shop shop);

    List<Shop> getShops(Shop shop);

}
