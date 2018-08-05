/***
 * @pName proback
 * @name MoneysMapper
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.Moneys;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/***
 * 资金变化仓储接口
 */
@Mapper
public interface MoneysMapper extends MyMapper<Moneys> {
}
