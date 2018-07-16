package com.wanhao.profront.service.impl.member;

import com.wanhao.profront.bean.member.Member;
import com.wanhao.profront.dao.member.MemberMapper;
import com.wanhao.profront.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
