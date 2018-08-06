/***
 * @pName proback
 * @name MoneysMapper
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.dao.finance;

import com.wanhao.proback.bean.finance.Moneys;
import com.wanhao.proback.bean.finance.MoneysView;
import com.wanhao.proback.bean.finance.TransactionsView;
import com.wanhao.proback.dao.provider.MoneysMapperProvider;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/***
 * 资金变化仓储接口
 */
@Mapper
public interface MoneysMapper extends MyMapper<Moneys> {
    @InsertProvider(type = MoneysMapperProvider.class, method = "batchInsert")
    /**
     * 批量插入记录 韦德 2018年8月5日18:10:27
     * @param moneyList
     * @return
     */
    int batchInsert(@Param("list") List<Moneys> moneyList);

    @Select("SELECT t1.*,t2.username FROM tb_moneys t1 LEFT JOIN tb_member t2 ON t1.from_uid = t2.id WHERE ${condition} ORDER BY add_date DESC LIMIT #{page},${limit}")
    /**
     * 查询-分页 韦德 2018年8月7日02:17:33
     * @param page
     * @param limit
     * @param trade_type
     * @param trade_date_begin
     * @param trade_date_end
     * @param where
     * @return
     */
    List<MoneysView> selectLimit(@Param("page") Integer page, @Param("limit") String limit
            , @Param("trade_type") Integer trade_type
            , @Param("beginTime") String trade_date_begin
            , @Param("endTime") String trade_date_end
            , @Param("condition")  String condition);

    @Select("SELECT COUNT(*) FROM tb_moneys")
    /**
     * 查询记录总数 韦德 2018年8月7日02:19:49
     * @return
     */
    int count();
}
