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
 *
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
        return memberMapper.selectByExample(example);

    }
}
