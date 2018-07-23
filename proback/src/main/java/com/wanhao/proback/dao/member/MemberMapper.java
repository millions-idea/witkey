package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
