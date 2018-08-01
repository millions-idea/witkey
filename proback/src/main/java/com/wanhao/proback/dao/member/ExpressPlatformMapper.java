/***
 * @pName proback
 * @name ExpressPlatformMapper
 * @user HongWei
 * @date 2018/8/1
 * @desc 快递空包平台
 */
package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.ExpressPlatform;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpressPlatformMapper extends MyMapper<ExpressPlatform> {
    /**
     * 查询快递平台集合 韦德 2018年8月1日15:26:35
     * @return
     */
    List<ExpressPlatform> selectAll();


    /**
     * 插入记录返回主键ID 韦德 2018年8月1日15:30:13
     * @param expressPlatform
     * @return
     */
    int insert(ExpressPlatform expressPlatform);
}