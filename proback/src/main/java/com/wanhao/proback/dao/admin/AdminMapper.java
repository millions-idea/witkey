package com.wanhao.proback.dao.admin;

import com.wanhao.proback.bean.admin.Admin;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiuLiHao on 2018/7/15 9:27.
 * 描述：Admin 数据库操作
 * 作者： LiuLiHao
 */
@Mapper
public interface AdminMapper extends MyMapper<Admin> {

}
