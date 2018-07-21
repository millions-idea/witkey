package com.wanhao.proback.service.impl;

import com.wanhao.proback.bean.Area;
import com.wanhao.proback.dao.AreaMapper;
import com.wanhao.proback.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/16 14:07.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional(readOnly = true)
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaMapper areaMapper;

    @Override
    @Cacheable(value = "province")
    public List<Area> getAllProvince() {
        System.out.println("不使用缓存");
        return areaMapper.getAllProvince();
    }


    @Override
    @Cacheable(value = "city",key = "#cid")
    public List<Area> getAllCity(Integer cid) {
        System.out.println("不使用缓存");
        return areaMapper.getAllCity(cid);
    }
}
