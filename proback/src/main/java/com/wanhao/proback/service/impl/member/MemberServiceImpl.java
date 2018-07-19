package com.wanhao.proback.service.impl.member;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.dao.member.MemberMapper;
import com.wanhao.proback.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/16 12:29.
 * 描述：会员操作
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMembers(Member member) {
        if (member.getPage() != null && member.getRows() != null) {
            PageHelper.startPage(member.getPage(), member.getRows());
        }
        Example example = new Example(Member.class);

        Example.Criteria criteria = example.createCriteria();
        //会员名
        if (member.getUsername() != null && member.getUsername().length() > 0) {
            criteria.andLike("username", "%" + member.getUsername() + "%");
        }
        //代理商
        if (member.getProxy() != null && member.getProxy().length() > 0) {
            criteria.andLike("proxy", "%" + member.getProxy() + "%");
        }

        //真实姓名
        if (member.getReal_name() != null && member.getReal_name().length() > 0) {
            criteria.andLike("real_name", "%" + member.getReal_name() + "%");
        }

        //手机号
        if (member.getMobile() != null && member.getMobile().length() > 0) {
            criteria.andLike("mobile",  member.getMobile() + "%");
        }
        //推荐人
        if (member.getInvite_id() != null && member.getInvite_id() > 0) {
            criteria.andLike("invite_id", member.getInvite_id() + "%");
        }
        //真实姓名
        if (member.getReal_name() != null && member.getReal_name().length() > 0) {
            criteria.andLike("real_name", "%" + member.getReal_name() + "%");
        }
        //用户类型
        if (member.getVipmodel() != null && member.getVipmodel().length() > 0) {
            criteria.andEqualTo("real_name",member.getVipmodel());
        }
        //是否已实名
        if (member.getIs_real_name() != null && member.getIs_real_name() > 0) {
            criteria.andEqualTo("is_real_name",member.getIs_real_name());
        }
        //性别
        if (member.getGender() != null && member.getGender().length() > 0 && !member.getGender().equals("0")) {
            criteria.andEqualTo("gender",member.getGender());
        }
        //所在地区
        if (member.getSheng() != null && member.getSheng().length() > 0) {
            criteria.andEqualTo("sheng",member.getSheng());
        }

        //是否已经实名
        if (member.getIs_real_name() != null) {
            criteria.andEqualTo("is_real_name",member.getIs_real_name());
        }

        //是否上传了图片
        if (member.getZheng() != null) {
            criteria.andIsNotNull("zheng");
        }
        if (member.getFan() != null) {
            criteria.andIsNotNull("fan");
        }
        if (member.getShou_chi() != null) {
            criteria.andIsNotNull("shou_chi");
        }

        return memberMapper.selectByExample(example);

    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(Member member) {
        return memberMapper.selectOne(member);
    }

    @Override
    public void updateMember(Member member) {
        memberMapper.updateByPrimaryKey(member);
    }
}
