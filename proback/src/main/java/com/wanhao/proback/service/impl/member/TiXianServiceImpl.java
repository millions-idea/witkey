package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.TiXian;
import com.wanhao.proback.dao.member.TiXianMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.member.TiXianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/25 19:00.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class TiXianServiceImpl extends BaseServiceImpl<TiXian> implements TiXianService {

    @Autowired
    TiXianMapper tiXianMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TiXian> getDatas(TiXian tiXian) {
        return null;
    }


}
