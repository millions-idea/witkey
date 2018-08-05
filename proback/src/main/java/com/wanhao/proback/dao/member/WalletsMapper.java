/***
 * @pName proback
 * @name WalletsMapper
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.Wallets;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/***
 * 财务钱包仓储接口
 */
@Mapper
public interface WalletsMapper extends MyMapper<Wallets> {

}
