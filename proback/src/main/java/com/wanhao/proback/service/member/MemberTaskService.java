package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.MemberTask;
import com.wanhao.proback.service.BaseService;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/26 17:50.
 * 描述：
 * 作者： LiuLiHao
 */
public interface MemberTaskService extends BaseService<MemberTask> {

    //条件查询
    List<MemberTask> getDatas(MemberTask memberTask);

}
