package com.wanhao.proback.service.shop;

import com.wanhao.proback.bean.shop.Shop;
import com.wanhao.proback.service.BaseService;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/21 14:48.
 * 描述：
 * 作者： LiuLiHao
 */
public interface ShopService extends BaseService<Shop> {

    List<Shop> getShops(Shop shop);

    //主键查询
    Shop getByPk(Integer id);

}
