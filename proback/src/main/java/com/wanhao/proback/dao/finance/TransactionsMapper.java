/***
 * @pName proback
 * @name TransactionsMapper
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.dao.finance;

import com.wanhao.proback.bean.finance.Amount;
import com.wanhao.proback.bean.finance.Transactions;
import com.wanhao.proback.bean.finance.TransactionsView;
import com.wanhao.proback.bean.member.ExpressOrdersView;
import com.wanhao.proback.bean.member.MemberView;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 交易流水仓储接口
 */
@Mapper
public interface TransactionsMapper extends MyMapper<Transactions> {

    @Select("SELECT t1.*,t2.username AS from_username,t3.username AS to_username FROM tb_transactions t1 " +
            "LEFT JOIN tb_member t2 ON t1.from_uid = t2.id " +
            "LEFT JOIN tb_member t3 ON t1.to_uid = t3.id " +
            "WHERE ${condition} ORDER BY trade_date DESC LIMIT #{page},${limit}" )
    /**
     * 查询-分页 韦德 2018年8月6日22:26:00
     * @param page
     * @param limit
     * @param where
     * @return
     */
    List<TransactionsView> selectLimit(@Param("page") Integer page, @Param("limit") String limit
            , @Param("trade_type") Integer trade_type
            , @Param("beginTime") String trade_date_begin
            , @Param("endTime") String trade_date_end
            , @Param("condition")  String condition);

    @Select("SELECT COUNT(*) FROM tb_transactions")
    /**
     * 查询记录总数 韦德 2018年8月4日09:59:05
     * @return
     */
    int count();

    @Select("SELECT SUM(trade_amount) AS amount,trade_type FROM tb_transactions WHERE from_uid = #{id} OR to_uid = #{id} GROUP BY trade_type;")
    /**
     * 统计账户的总收入与总支出情况 韦德 2018年8月7日00:43:31
     * @param id
     * @return
     */
    List<Amount> getAccountAmount(@Param("id") Integer id);

}
