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
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ExpressPostalAddressMapper extends MyMapper<ExpressPostalAddress> {

    @Select("SELECT COUNT(*) FROM tb_express_postal_address WHERE user_id = #{userId}")
    /**
     * 查询已创建数量 韦德 2018年8月9日17:31:05
     * @param userId
     * @return
     */
    int selectCreateCount(@Param("userId") Integer userId);

    @Update("UPDATE tb_express_postal_address SET address_id = #{address_id},user_id = #{user_id}, city_id = #{city_id} ,postal_code = #{postal_code},address = #{address} ,real_name = #{real_name} ,phone = #{phone},sort = #{sort},remark = #{remark} WHERE address_id = #{address_id} AND user_id = #{user_id}")
    /**
     * 根据主键更新 韦德 2018年8月9日21:57:37
     * @param v
     */
    int updateById(ExpressPostalAddress v);

    @Select("SELECT * FROM tb_express_postal_address WHERE user_id = #{userId} ORDER BY sort ASC")
    /**
     * 查询并排序 韦德 2018年8月9日22:08:24
     * @param userId
     * @return
     */
    List<ExpressPostalAddress> selectByOrder(@Param("userId") Integer userId);
}
