/***
 * @pName proback
 * @name WalletsMapper
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.dao.finance;

import com.wanhao.proback.bean.finance.Wallets;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/***
 * 财务钱包仓储接口
 */
@Mapper
public interface WalletsMapper extends MyMapper<Wallets> {

    @Update("UPDATE tb_wallets SET balance = balance - #{amount}, edit_date = NOW(), version = version + 1 WHERE `user_id`=#{formUid} AND balance >= #{amount} AND version=#{version}")
    /**
     * 扣减余额 韦德 2018年8月5日17:13:19
     * @param formUid
     * @param amount
     * @param version
     * @return
     */
    int reduceBalance(@Param("formUid") Integer formUid,  @Param("amount") Double amount,  @Param("version") Integer version);

   // @Update("UPDATE tb_wallets SET balance = balance + #{amount}, edit_date = NOW() WHERE `user_id`=#{formUid} AND balance >= #{amount}")
    /**
     * 增加余额 韦德 2018年8月5日17:14:27
     * @param formUid
     * @param amount
     * @return
     */
    @Update("UPDATE tb_wallets SET balance = balance + #{amount}, edit_date = NOW() WHERE `user_id`=#{formUid} ")
    int addBalance(@Param("formUid") Integer formUid, @Param("amount") Double amount);


    @Select("SELECT * FROM tb_wallets WHERE user_id=#{user_id}")
    /**
     * 根据用户id查询钱包信息 韦德 2018年8月5日17:19:40
     * @param user_id
     * @return
     */
    Wallets selectOneByUid(@Param("user_id") Integer user_id);

    @Select("<script>" +
            "<foreach collection='list' item='id' separator='union all'> " +
            "            SELECT * FROM tb_wallets WHERE user_id = #{id}" +
            "        </foreach>" +
            "</script>")
    /**
     * 批量查询用户钱包信息 韦德 2018年8月8日18:07:44
     * @param uidList
     * @return
     */
    List<Wallets> selectInUidByUnionAll(@Param("list") List<Integer> uidList);
}
