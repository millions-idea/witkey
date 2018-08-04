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
            "WHERE t1.user_id = t2.user_id AND t1.isDelete=0 " +
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


    @Update("UPDATE tb_express_orders SET edit_date=NOW(),express_id=#{express_id},status=#{status},isEnable=#{isEnable} WHERE order_id=#{order_id} AND user_id=#{user_id} AND isDelete=0")
    /**
     * 编辑 韦德 2018年8月3日23:17:16
     * @param expressOrders
     * @return
     */
    int updateSingle(ExpressOrders expressOrders);


    @Update("UPDATE tb_express_orders SET edit_date=NOW(),express_id=#{express_id} WHERE (status!=2 AND status!=3) AND order_id=#{order_id} AND user_id=#{user_id} AND isDelete=0")
    /**
     * 编辑运单号 韦德 2018年8月4日23:23:20
     * @param expressOrders
     * @return
     */
    int updateExpressId(ExpressOrders expressOrders);


    @Update("UPDATE tb_express_orders SET edit_date=NOW(),isEnable=0,isDelete=1 WHERE order_id IN(${id})")
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

    @Update("UPDATE tb_express_orders SET edit_date=NOW(),status=#{status},express_id=#{express_id} WHERE (status!=2 AND status!=3) AND order_id=#{order_id} AND user_id=#{user_id} AND isDelete=0")
    /**
     * 编辑状态 韦德 2018年8月5日00:15:02
     * @param param
     * @return
     */
    int updateStatus(ExpressOrders param);

    @Update("UPDATE tb_express_orders SET edit_date=NOW(),status=1,express_id='1' WHERE status = 0 AND order_id IN(${id}) AND isDelete=0")
    /**
     * 编辑状态 韦德 2018年8月5日00:15:02
     * @param param
     * @return
     */
    int updateStatuses(@Param("id") String id);
}