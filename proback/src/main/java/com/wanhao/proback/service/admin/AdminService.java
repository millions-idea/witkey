package com.wanhao.proback.service.admin;

import com.wanhao.proback.bean.admin.Admin;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/15 9:32.
 * 描述：
 * 作者： LiuLiHao
 */
public interface AdminService {

    Admin findByName(String username);

    /**
     * 查找所有
     * @return
     */
    List<Admin> findAll();
}
