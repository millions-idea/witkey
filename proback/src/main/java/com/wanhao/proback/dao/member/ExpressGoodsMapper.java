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
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 快递平台商品表仓储接口
 */
@Mapper
public interface ExpressGoodsMapper extends MyMapper<ExpressGoods> {

    @Select("SELECT t1.*,t2.`name` AS category_name,t3.`name` AS expp_name  " +
            "FROM tb_express_goods t1 " +
            "LEFT JOIN tb_business_brands t2 ON t1.category_id = t2.business_id " +
            "LEFT JOIN tb_express_platforms t3 ON t1.expp_id = t3.expp_id " +
            "WHERE t1.isDelete = 0 ${condition} ORDER BY t1.goods_id DESC LIMIT #{page},${limit}")
    /**
     * 查询快递平台商品集合 韦德 2018年8月2日23:32:44
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    List<ExpressGoodsView> selectLimit(@Param("page") Integer page, @Param("limit") String limit, @Param("condition")  String condition);

    @Select("SELECT COUNT(*) FROM tb_express_goods WHERE isDelete=0")
    /**
     * 查询快递商品记录总数 韦德 2018年8月2日23:43:13
     * @return
     */
    int count();

    @Update("UPDATE tb_express_goods SET expp_id=#{expp_id}, category_id=#{category_id}, `name`=#{name}, price=#{price}, rate=#{rate}, isEnable=#{isEnable} WHERE goods_id=#{goods_id} AND isDelete=0 ")
    /**
     * 更新商品 韦德 2018年8月3日16:06:10
     * @param v
     * @return
     */
    int updateSingle(ExpressGoods v);

    @Insert("INSERT INTO tb_express_goods(expp_id,category_id,name,price,rate,isEnable,isDelete) VALUES(#{expp_id}, #{category_id}, #{name}, #{price}, #{rate}, ${isEnable}, 0)")
    /**
     * 新增商品 韦德 2018年8月3日17:15:08
     * @param v
     * @return
     */
    int insertSingle(ExpressGoods v);

    @Update("UPDATE tb_express_goods SET edit_date=NOW(),isEnable=0,isDelete=1 WHERE goods_id IN(${id})")
    /**
     * 删除商品 韦德 2018年8月3日21:50:01
     * @param id
     * @return
     */
    int deleteBy(@Param("id") String id);

    @Select("SELECT t1.*,t2.`name` AS category_name,t3.`name` AS expp_name  " +
            "FROM tb_express_goods t1 " +
            "LEFT JOIN tb_business_brands t2 ON t1.category_id = t2.business_id " +
            "LEFT JOIN tb_express_platforms t3 ON t1.expp_id = t3.expp_id " +
            "WHERE t1.isDelete = 0 AND t1.isEnable = 1")
    /**
     * 查询集合 韦德 2018年8月7日23:45:48
     * @return
     */
    List<ExpressGoodsView> selectList();

    @Select("SELECT * FROM tb_express_goods WHERE goods_id=#{goods_id} AND isDelete=0")
    /**
     * 根据id查询信息 韦德 2018年8月8日16:59:02
     * @param goodsId
     * @return
     */
    ExpressGoods selectById(@Param("goods_id") Integer goodsId);

    @Select("<script>" +
            "<foreach collection='list' item='id' separator='union all'> " +
            "            SELECT * FROM tb_express_goods WHERE goods_id = #{id} AND isDelete=0  " +
            "        </foreach>" +
            "</script>")
    /**
     * 根据多个id查询商品信息 韦德 2018年8月8日18:15:06
     * @param goodsList
     * @return
     */
    List<ExpressGoods> selectInIdByUnionAll(@Param("list") List<Integer> goodsList);
}
