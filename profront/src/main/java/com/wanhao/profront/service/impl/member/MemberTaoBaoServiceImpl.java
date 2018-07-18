package com.wanhao.profront.service.impl.member;

import com.wanhao.profront.bean.member.MemberTaoBao;
import com.wanhao.profront.dao.member.MemberTaoBaoMapper;
import com.wanhao.profront.service.member.MemberTaoBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/18 21:00.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class MemberTaoBaoServiceImpl implements MemberTaoBaoService {

    @Autowired
    MemberTaoBaoMapper taoBaoMapper;

    @Override
    public void addMemberTaoBao(MemberTaoBao taoBao) {
        taoBaoMapper.insert(taoBao);

    }

    @Override
    @Transactional
    public List<MemberTaoBao> queryMemberBuyList(MemberTaoBao taoBao) {
        return null;
    }
}

