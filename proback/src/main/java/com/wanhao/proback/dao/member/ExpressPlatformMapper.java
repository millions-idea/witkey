/***
 * @pName proback
 * @name ExpressPlatformMapper
 * @user HongWei
 * @date 2018/8/1
 * @desc 快递平台渠道表仓储接口
 */
package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.ExpressPlatform;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 快递平台渠道表仓储接口
 */
@Mapper
public interface ExpressPlatformMapper  extends MyMapper<ExpressPlatform>{

    @Select("SELECT * FROM tb_express_platforms WHERE isDelete=0 ${condition} LIMIT #{page},${limit}")
    /**
     * 查询快递平台集合 韦德 2018年8月1日15:26:35
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    List<ExpressPlatform> selectLimit(@Param("page") Integer page, @Param("limit") String limit,@Param("condition")  String condition);

    @Insert("INSERT INTO tb_express_platforms(`name`,url,isEnable) VALUES(#{name},#{url},${isEnable})")
            @Options(keyProperty = "expp_id",useGeneratedKeys = true)
    /**
     * 插入记录返回主键ID 韦德 2018年8月1日15:30:13
     * @param expressPlatform
     * @return
     */
    int insertSingle(ExpressPlatform expressPlatform);

    @Update("UPDATE tb_express_platforms SET `name`=#{name}, url=#{url}, isEnable=#{isEnable} WHERE expp_id=#{expp_id} AND isDelete=0")
    /**
     * 更新记录 韦德 2018年8月2日16:50:20
     * @param v
     * @return
     */
    int updateSingle(ExpressPlatform v);

    @Update("UPDATE tb_express_platforms SET isEnable=0,isDelete=1 WHERE expp_id IN(${expp_id})")
    /**
     * 删除记录 韦德 2018年8月2日17:46:53
     * @param expp_id
     * @return
     */
    int deleteBy(@Param("expp_id") String expp_id);

    @Select("SELECT COUNT(*) FROM tb_express_platforms WHERE isDelete=0")
    /**
     * 查询记录总数 韦德 2018年8月2日18:54:32
     * @return
     */
    int count();

    @Select("SELECT * FROM tb_express_platforms WHERE isDelete=0 ORDER BY expp_id DESC")
    /**
     * 查询快递平台集合-不带分页 韦德 2018年8月3日13:24:37
     * @return
     */
    List<ExpressPlatform> selectList();
}
