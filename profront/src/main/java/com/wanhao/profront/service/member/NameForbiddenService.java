package com.wanhao.profront.service.member;


import com.wanhao.profront.bean.member.NameForbidden;

/**
 * Created by LiuLiHao on 2018/7/15 22:09.
 * 描述：
 * 作者： LiuLiHao
 */
public interface NameForbiddenService {
    NameForbidden query();

    void update(NameForbidden name);

}
