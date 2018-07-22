package com.wanhao.proback.service;

import com.wanhao.proback.bean.Setting;

/**
 * Created by LiuLiHao on 2018/7/21 15:44.
 * 描述： 系统设置
 * 作者： LiuLiHao
 */
public interface SettingService {

    void update(Setting setting);

    Setting query();

}
