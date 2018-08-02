/***
 * @pName proback
 * @name ExpressGoodsMapper
 * @user HongWei
 * @date 2018/8/2
 * @desc 快递平台商品表仓储接口
 */
package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.ExpressGoods;
import com.wanhao.proback.bean.member.ExpressGoodsView;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 快递平台商品表仓储接口
 */
@Mapper
public interface ExpressGoodsMapper extends MyMapper<Member> {

    /*@Select("SELECT * FROM tb_express_goods WHERE isDelete=0 ${condition} LIMIT #{page},${limit}")*/
    @Select("SELECT t1.*,t2.`name` AS category_name " +
            "FROM tb_express_goods t1 LEFT JOIN tb_business_brands t2 ON t1.category_id = t2.business_id " +
            "WHERE t1.isDelete = 0 ${condition} LIMIT #{page},${limit}")
    /**
     * 查询快递平台商品集合 韦德 2018年8月2日23:32:44
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    List<ExpressGoodsView> selectLimit(@Param("page") Integer page, @Param("limit") String limit, @Param("condition")  String condition);

    @Select("SELECT * FROM tb_express_goods WHERE isDelete=0")
    /**
     * 查询快递商品记录总数 韦德 2018年8月2日23:43:13
     * @return
     */
    int count();
}
