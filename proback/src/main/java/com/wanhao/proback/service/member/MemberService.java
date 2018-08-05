package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.util.InviteResult;

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

    void addMember(Member member);

    List<Member> loginMember(Member member);

    List<Member> loginMemberByMobile(Member member);

    /**
     * 用户名查找
     * @param invite_name
     */
    Member getMemberByUserName(String invite_name);

    //一级下线
    List<Member> getMemberFristInvite(Integer id);

    //二级下线
    List<Member> getMemberSecondInvite(Integer memid);

    /**
     * 查询推广人数排行列表
     * @return
     */
    List<InviteResult> getInviteData();



    /**
     * 查询一周推广人数排行列表
     * @return
     */
    List<InviteResult> getInviteDataByWeek();


    /**
     * 查询一月推广人数排行列表
     * @return
     */
    List<InviteResult> getInviteDataByMonth();


    void delete(String id);

    void agreeAll(String id);

    void rejectAll(String id, String reason);
}
