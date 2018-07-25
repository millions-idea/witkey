package com.wanhao.proback.service.shop;

import com.wanhao.proback.bean.shop.Goods;
import com.wanhao.proback.service.BaseService;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/24 16:18.
 * 描述：
 * 作者： LiuLiHao
 */
public interface GoodsService extends BaseService<Goods> {


    List<Goods> getGoods(Goods goods);

}
