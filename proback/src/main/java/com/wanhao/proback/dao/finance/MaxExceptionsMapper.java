/***
 * @pName proback
 * @name MaxExceptionsMapper
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.dao.finance;

import com.wanhao.proback.bean.finance.MaxExceptions;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 重要异常日志仓储接口
 */
@Mapper
public interface MaxExceptionsMapper extends MyMapper<MaxExceptions> {
    @Insert("INSERT INTO tb_max_exceptions(user_id, username, body, add_date) VALUES(#{user_id},(SELECT username FROM tb_member WHERE id=#{user_id}), #{body}, NOW())")
    /**
     * 插入单条记录 韦德 2018年8月5日19:27:36
     * @param maxExceptions
     * @return
     */
    int insertSingle(MaxExceptions maxExceptions);
}
