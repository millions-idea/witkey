/***
 * @pName proback
 * @name ExpressPostalAddressMapper
 * @user HongWei
 * @date 2018/8/7
 * @desc
 */
package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.ExpressPostalAddress;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExpressPostalAddressMapper extends MyMapper<ExpressPostalAddress> {

    @Select("SELECT COUNT(*) FROM tb_express_postal_address WHERE user_id = #{userId}")
    /**
     * 查询已创建数量 韦德 2018年8月9日17:31:05
     * @param userId
     * @return
     */
    int selectCreateCount(@Param("userId") Integer userId);
}
