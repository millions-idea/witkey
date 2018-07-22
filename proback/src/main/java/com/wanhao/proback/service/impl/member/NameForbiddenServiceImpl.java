package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.NameForbidden;
import com.wanhao.proback.dao.member.NameForbiddenMapper;
import com.wanhao.proback.service.member.NameForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LiuLiHao on 2018/7/15 22:10.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class NameForbiddenServiceImpl implements NameForbiddenService {
    @Autowired
    NameForbiddenMapper nameForbiddenMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "forbidden")
    public NameForbidden query() {
        return nameForbiddenMapper.selectAll().get(0);
    }

    @Override
    public void update(NameForbidden name) {
        nameForbiddenMapper.updateByPrimaryKeySelective(name);
    }
}
