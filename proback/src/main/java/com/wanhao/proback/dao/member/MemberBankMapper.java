package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.MemberBank;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 描述：
 * 作者： LiuLiHao
 */
@Mapper
public interface MemberBankMapper extends MyMapper<MemberBank> {

    /**
     * 通过所有
     * @param id
     * @return
     */
    @Update("UPDATE tb_member_bank SET is_auth=1 WHERE id IN( ${id} )")
    int agreeAllBank(@Param("id")String id);

    /**
     * 拒绝所有
     * @param id
     * @param reason
     * @return
     */
    @Update("UPDATE tb_member_bank SET is_auth=2,remark=#{reason} WHERE id IN( ${id} )")
    int rejectAllBuyBank(@Param("id")String id,@Param("reason") String reason);

}
