/***
 * @pName proback
 * @name ExpressOrders
 * @user HongWei
 * @date 2018/8/4
 * @desc
 */
package com.wanhao.proback.dao.member;


import com.wanhao.proback.bean.member.ExpressOrders;
import com.wanhao.proback.bean.member.ExpressOrdersView;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 快递订单仓储接口
 */
@Mapper
public interface ExpressOrdersMapper {
    @Select("SELECT t1.*,t1.remark AS orders_remark " +
            ",t2.phone,t2.address AS send_address,t2.real_name,t2.postal_code,t2.remark AS address_remark " +
            ",t3.username " +
            "FROM tb_express_orders t1 " +
            "LEFT JOIN tb_express_postal_address t2 ON t1.send_address_id = t2.address_id " +
            "LEFT JOIN tb_member t3 ON t1.user_id = t3.id " +
            "WHERE t1.user_id = t2.user_id " +
            "${condition} LIMIT #{page},${limit} ")
    /**
     * 查询-分页 韦德 2018年8月3日11:40:57
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    List<ExpressOrdersView> selectLimit(@Param("page") Integer page, @Param("limit") String limit, @Param("condition")  String condition);

    @Select("SELECT * FROM tb_express_orders WHERE isDelete=0 ${condition}")
    /**
     * 查询-不带分页 韦德 2018年8月4日09:59:05
     */
    List<ExpressOrdersView> selectBy(@Param("condition")  String condition);


    @Update("UPDATE tb_express_orders SET `user_id`=#{user_id}, `send_address_id`=#{send_address_id}, recv_address=#{recv_address}, express_id=#{express_id},  `amount`=#{amount}, `status`=#{status}, `add_date`=#{add_date}, `edit_date`=#{edit_date}, `remark`=#{remark}, isEnable=#{isEnable} WHERE business_id=#{order_id} AND isDelete=0")
    /**
     * 编辑 韦德 2018年8月3日23:17:16
     * @param businessBrands
     * @return
     */
    int updateSingle(ExpressOrders businessBrands);

    @Update("UPDATE tb_express_orders SET isEnable=0,isDelete=1 WHERE business_id IN(${id})")
    /**
     * 删除 韦德 2018年8月4日09:59:05
     * @param id
     * @return
     */
    int deleteBy(@Param("id") String id);

    @Insert("INSERT INTO tb_express_orders(user_id, send_address_id, recv_address, express_id, amount, status, add_date, edit_date, remark, isEnable, isDelete) " +
            "VALUES(#{user_id}, #{send_address_id}, #{recv_address}, #{express_id}, #{amount}, #{status}, #{add_date}, #{edit_date}, #{remark}, ${isEnable}, 0)")
    /**
     * 添加 韦德 2018年8月4日09:59:05
     * @param businessBrands
     * @return
     */
    int insertSingle(ExpressOrders businessBrands);

    @Select("SELECT COUNT(*) FROM tb_express_orders WHERE isDelete=0")
    /**
     * 查询记录总数 韦德 2018年8月4日09:59:05
     * @return
     */
    int count();
}