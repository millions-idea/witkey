package com.wanhao.proback.dao;

import com.wanhao.proback.bean.Area;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/16 13:57.
 * 描述： 地区操作
 * 作者： LiuLiHao
 */
@Mapper
public interface AreaMapper extends MyMapper<Area> {
    /**
     * 查询所有省
     * @return
     */
    @Select("SELECT * FROM tb_area WHERE parentid=0 ORDER BY codeid ASC")
    List<Area> getAllProvince();


    /**
     * 查询所有市
     * @return
     */
    @Select("SELECT * FROM tb_area WHERE parentid=#{cid} ORDER BY codeid ASC")
    List<Area> getAllCity(Integer cid);


}
