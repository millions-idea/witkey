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
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("SELECT * FROM tb_business_brands WHERE isDelete=0 ${condition} ORDER BY business_id DESC")
    /**
     * 查询品牌公司集合-不带分页 韦德 2018年8月3日11:44:02
     */
    List<BusinessBrands> selectBy(@Param("condition")  String condition);
}
