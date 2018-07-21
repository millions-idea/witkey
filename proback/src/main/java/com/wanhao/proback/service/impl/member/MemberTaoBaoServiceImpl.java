package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.MemberTaoBao;
import com.wanhao.proback.dao.member.MemberTaoBaoMapper;
import com.wanhao.proback.service.member.MemberTaoBaoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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
    @Transactional(readOnly = true)
    @Cacheable(value = "memberslist")
    public List<MemberTaoBao> queryMemberBuyList(MemberTaoBao taoBao) {
        Example example = new Example(MemberTaoBao.class);

        Example.Criteria criteria = example.createCriteria();

        if (taoBao.getMem_id()!=null){
            //会员名
            criteria.andEqualTo("mem_id", taoBao.getMem_id());
        }
        //账号类型
        if (StringUtils.isNotBlank(taoBao.getAccount_type())){
            criteria.andEqualTo("account_type", taoBao.getAccount_type());
        }

        //账号类型
        if (StringUtils.isNotBlank(taoBao.getAccount())){
            criteria.andEqualTo("account", taoBao.getAccount());
        }
        return taoBaoMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "members")
    public MemberTaoBao getOne(Integer id) {
        MemberTaoBao memberTaoBao = new MemberTaoBao();
        memberTaoBao.setId(id);
        return taoBaoMapper.selectOne(memberTaoBao);
    }
}

