package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.NameForbidden;
import com.wanhao.proback.dao.member.NameForbiddenMapper;
import com.wanhao.proback.service.member.NameForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional(readOnly = true)
    public NameForbidden query() {
        NameForbidden redis = (NameForbidden) redisTemplate.opsForValue().get("nameForbidden");
        if (redis!=null){
            return redis;
        }
        NameForbidden nameForbidden = nameForbiddenMapper.selectAll().get(0);
        redisTemplate.opsForValue().set("nameForbidden",nameForbidden);
        return nameForbidden;
    }

    @Override
    public void update(NameForbidden name) {
        nameForbiddenMapper.updateByPrimaryKeySelective(name);
        redisTemplate.opsForValue().set("nameForbidden",name);
    }
}
