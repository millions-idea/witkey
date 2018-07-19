package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.Member;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/16 12:26.
 * 描述：
 * 作者： LiuLiHao
 */
public interface MemberService {

    /**
     * 条件分页查询
     * @param member
     * @return
     */
    public List<Member> getMembers(Member member);

    /**
     * 主键查询
     * @param member
     * @return
     */
    Member getMember(Member member);

    void updateMember(Member member);

}
