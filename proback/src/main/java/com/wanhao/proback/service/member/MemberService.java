package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.member.MemberView;
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

    /**
     * 根据id查询用户,钱包信息 韦德 2018年8月7日00:18:34
     * @param id
     * @return
     */
    MemberView getMemberByIdForDB(Integer id);

    /**
     * 根据id查询用户的缓存 韦德 2018年8月7日00:32:41
     * @param id
     * @return
     */
    MemberView getMemberByIdForCache(Integer id);

    /**
     * 根据id查询用户视图信息-先查缓存后查数据库 韦德 2018年8月7日00:37:52
     * @param id
     * @return
     */
    MemberView getMemberById(Integer id);
}
