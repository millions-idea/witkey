package com.wanhao.proback.service.shop;

import com.wanhao.proback.bean.shop.Goods;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/24 16:18.
 * 描述：
 * 作者： LiuLiHao
 */
public interface GoodsService {


    List<Goods> getGoods(Goods goods);


    void update(Goods goods);

    void add(Goods goods);

    void delete(int id);

}
