package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.util.InviteResult;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/16 12:25.
 * 描述：
 * 作者： LiuLiHao
 */
@Mapper
public interface MemberMapper extends MyMapper<Member> {

//    @Select("SELECT * FROM tb_member WHERE invite_id=#{invite_id}")
//    List<Member> getInviteFirstList(Integer invite_id);
//
//
//    @Select("SELECT * FROM tb_member WHERE invite_id=#{invite_id}")
//    List<Member> getInviteSeconList(Integer invite_id);


    /**
     * 查询总推广人数排行列表
     * @return
     */
    @Select("SELECT COUNT(1) pep_count ,invite_id,username " +
            "FROM tb_member WHERE invite_id IS NOT NULL " +
            "GROUP BY invite_id ORDER BY pep_count DESC")
    List<InviteResult> getInviteData();


    /**
     * 查询一周推广人数排行列表
     * @return
     */
    @Select("SELECT COUNT(1) pep_count ,invite_id,username " +
            "FROM tb_member " +
            "WHERE invite_id IS NOT  NULL  " +
            "AND " +
            "WEEKOFYEAR(CURDATE()) = WEEKOFYEAR(regist_time) " +
            "GROUP BY invite_id ORDER BY pep_count DESC")
    List<InviteResult> getInviteDataByWeek();


    /**
     * 查询一月推广人数排行列表
     * @return
     */
    @Select("SELECT COUNT(1) pep_count ,invite_id,username " +
            "FROM tb_member " +
            "WHERE invite_id IS NOT  NULL  " +
            "AND " +
            "MONTH(CURDATE()) = MONTH(regist_time) " +
            "GROUP BY invite_id ORDER BY pep_count DESC")
    List<InviteResult> getInviteDataByMonth();


}
