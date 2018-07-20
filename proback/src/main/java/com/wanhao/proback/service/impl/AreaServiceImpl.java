package com.wanhao.proback.service.impl;

import com.wanhao.proback.bean.Area;
import com.wanhao.proback.dao.AreaMapper;
import com.wanhao.proback.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Area> getAllProvince() {
        return areaMapper.getAllProvince();
    }


    @Override
    public List<Area> getAllCity(Integer cid) {
        return areaMapper.getAllCity(cid);
    }
}
