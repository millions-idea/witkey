/***
 * @pName proback
 * @name TransactionsMapper
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.dao.finance;

import com.wanhao.proback.bean.member.Transactions;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 交易流水仓储接口
 */
@Mapper
public interface TransactionsMapper extends MyMapper<Transactions> {

}
