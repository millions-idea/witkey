package com.wanhao.proback.service.admin;

import com.wanhao.proback.bean.admin.Admin;

/**
 * Created by LiuLiHao on 2018/7/15 9:32.
 * 描述：
 * 作者： LiuLiHao
 */
public interface AdminService {

    Admin findByName(String username);

}
