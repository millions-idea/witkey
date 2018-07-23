package com.wanhao.proback.service.impl.vip;

import com.wanhao.proback.bean.vip.Vip;
import com.wanhao.proback.dao.vip.VipMapper;
import com.wanhao.proback.service.vip.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/23 11:43.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class VipServiceImpl implements VipService {

    @Autowired
    VipMapper vipMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "vip")
    public List<Vip> getAll() {
        return vipMapper.selectAll();
    }
}
