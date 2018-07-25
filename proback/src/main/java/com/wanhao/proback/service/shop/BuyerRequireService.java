package com.wanhao.proback.service.shop;

import com.wanhao.proback.bean.shop.BuyerRequire;
import com.wanhao.proback.service.BaseService;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/24 16:21.
 * 描述：
 * 作者： LiuLiHao
 */
public interface BuyerRequireService extends BaseService<BuyerRequire> {

    List<BuyerRequire> getBuyerRequires(BuyerRequire require);

}
