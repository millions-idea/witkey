/***
 * @pName proback
 * @name MoneysMapper
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.dao.finance;

import com.wanhao.proback.bean.member.Moneys;
import com.wanhao.proback.dao.provider.MoneysMapperProvider;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
