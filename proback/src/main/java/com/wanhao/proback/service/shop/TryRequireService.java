package com.wanhao.proback.service.shop;

import com.wanhao.proback.bean.shop.TryRequire;
import com.wanhao.proback.service.BaseService;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/24 16:20.
 * 描述：
 * 作者： LiuLiHao
 */
public interface TryRequireService extends BaseService<TryRequire> {

    List<TryRequire> get(TryRequire require);


}
