package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.TiXian;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/25 18:59.
 * 描述：
 * 作者： LiuLiHao
 */
@Mapper
public interface TiXianMapper extends MyMapper<TiXian> {


    /**
     * 今日提现数据
     * @return
     */
    @Select("SELECT * FROM tb_tixian WHERE memid=#{id} AND DATE( shenqing_shijian) = CURDATE()")
    List<TiXian> getTodayList(Integer id);


}
