package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.TiXian;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select(" SELECT t.*,m.real_name,m.mobile,m.real_name FROM tb_tixian t JOIN tb_member m ON t.memid=m.id  WHERE t.is_delete=0  ${condition}")
    List<TiXian> getDatas(@Param("condition")  String condition);

    /**
     * 拒绝提现
     * @param id
     * @param reason
     * @return
     */
    @Update("UPDATE tb_tixian SET flag=3,remark=#{reason} WHERE id IN( ${id} ) ")
    int rejectAllTiXian(@Param("id")String id, @Param("reason")String reason);


}
