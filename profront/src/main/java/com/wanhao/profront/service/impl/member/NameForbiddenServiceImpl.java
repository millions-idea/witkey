package com.wanhao.profront.service.impl.member;

import com.wanhao.profront.bean.member.NameForbidden;
import com.wanhao.profront.dao.member.NameForbiddenMapper;
import com.wanhao.profront.service.member.NameForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public NameForbidden query() {
        return nameForbiddenMapper.selectAll().get(0);
    }

    @Override
    public void update(NameForbidden name) {
        nameForbiddenMapper.updateByPrimaryKey(name);
    }
}
