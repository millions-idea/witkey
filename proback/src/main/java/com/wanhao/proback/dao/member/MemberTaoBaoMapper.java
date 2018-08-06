package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.MemberTaoBao;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by LiuLiHao on 2018/7/16 18:42.
 * 描述： 淘宝买号
 * 作者： LiuLiHao
 */
@Mapper
public interface MemberTaoBaoMapper extends MyMapper<MemberTaoBao> {
    /**
     * 同意全部买号
     * @param id
     * @return
     */
    @Update("UPDATE tb_member_taobao SET is_pass=1 WHERE id IN( ${id} )")
    int agreeAllBuyAccount(@Param("id")String id);

    /**
     * 拒绝全部买号
     * @param id
     * @param reason
     * @return
     */
    @Update("UPDATE tb_member_taobao SET is_pass=2,remark=#{reason} WHERE id IN( ${id} )")
    int rejectAllBuyAccount(@Param("id")String id, @Param("reason")String reason);

}
