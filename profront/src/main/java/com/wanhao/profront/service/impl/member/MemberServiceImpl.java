package com.wanhao.profront.service.impl.member;

import com.wanhao.profront.bean.member.Member;
import com.wanhao.profront.dao.member.MemberMapper;
import com.wanhao.profront.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by LiuLiHao on 2018/7/16 18:41.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public void addMember(Member member) {
        memberMapper.insert(member);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findByName(String name) {
        Example example = new Example(Member.class);

        Example.Criteria criteria = example.createCriteria();
        //会员名
        criteria.andEqualTo("username", name);

        return memberMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findByKey(Member member) {
        return memberMapper.selectOne(member);
    }

    @Override
    public void updateMember(Member member,Integer version) {
        //判断幂等性
        if (version.equals(member.getVersion())){
            member.setVersion(member.getVersion()+1);
            memberMapper.updateByPrimaryKey(member);
        }
    }
}
