package com.wanhao.proback.dao.member;

import com.wanhao.proback.bean.member.NameForbidden;
import com.wanhao.proback.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiuLiHao on 2018/7/15 22:06.
 * 描述： 查询和设置禁用用户名
 * 作者： LiuLiHao
 */
@Mapper
public interface NameForbiddenMapper extends MyMapper<NameForbidden> {

    void updateFor(NameForbidden nameForbidden);

}
