/***
 * @pName proback
 * @name BusinessBrandsMapper
 * @user HongWei
 * @date 2018/8/3
 * @desc 电商公司品牌仓储接口
 */
package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.BusinessBrands;
import com.wanhao.proback.bean.member.ExpressPlatform;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 电商公司品牌仓储接口
 */
@Mapper
public interface BusinessBrandsMapper {
    @Select("SELECT * FROM tb_business_brands WHERE isDelete=0 ${condition} LIMIT #{page},${limit}")
    /**
     * 查询品牌公司集合-分页 韦德 2018年8月3日11:40:57
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    List<BusinessBrands> selectLimit(@Param("page") Integer page, @Param("limit") String limit, @Param("condition")  String condition);

    @Select("SELECT * FROM tb_business_brands WHERE isDelete=0 ${condition}")
    /**
     * 查询品牌公司集合-不带分页 韦德 2018年8月3日11:44:02
     */
    List<BusinessBrands> selectBy(@Param("condition")  String condition);


    @Update("UPDATE tb_business_brands SET `name`=#{name}, isEnable=#{isEnable} WHERE business_id=#{business_id} AND isDelete=0")
    /**
     * 编辑品牌 韦德 2018年8月3日23:17:16
     * @param businessBrands
     * @return
     */
    int updateSingle(BusinessBrands businessBrands);

    @Update("UPDATE tb_business_brands SET isEnable=0,isDelete=1 WHERE business_id IN(${id})")
    /**
     * 删除品牌 韦德 2018年8月3日21:50:01
     * @param id
     * @return
     */
    int deleteBy(@Param("id") String id);

    @Insert("INSERT INTO tb_business_brands(name,isEnable,isDelete) VALUES(#{name}, ${isEnable}, 0)")
    /**
     * 添加品牌 韦德 2018年8月3日23:34:37
     * @param businessBrands
     * @return
     */
    int insertSingle(BusinessBrands businessBrands);

    @Select("SELECT COUNT(*) FROM tb_business_brands WHERE isDelete=0")
    /**
     * 查询记录总数 韦德 2018年8月2日18:54:32
     * @return
     */
    int count();
}
