package com.wanhao.proback.service.impl;

import com.wanhao.proback.bean.Setting;
import com.wanhao.proback.dao.SettingMapper;
import com.wanhao.proback.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LiuLiHao on 2018/7/21 15:45.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
@CacheConfig(cacheNames = "setting")
public class SettingServiceImpl implements SettingService {

    @Autowired
    SettingMapper settingMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void update(Setting setting) {
        redisTemplate.delete("setting");
        setting.setId(1);
        settingMapper.updateByPrimaryKeySelective(setting);
    }

    @Override
    @Cacheable(value = "setting")
    public Setting query() {
        Setting setting = settingMapper.selectAll().get(0);
        redisTemplate.opsForValue().set("setting",setting);
        return setting;
    }

}
