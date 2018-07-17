package com.wanhao.profront.service.member;

import com.wanhao.profront.bean.member.Member;

/**
 * Created by LiuLiHao on 2018/7/16 18:40.
 * 描述：
 * 作者： LiuLiHao
 */
public interface MemberService {

    void addMember(Member member);

    Member findByName(String name);

}
