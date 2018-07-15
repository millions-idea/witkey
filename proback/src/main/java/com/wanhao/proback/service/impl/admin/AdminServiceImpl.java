package com.wanhao.proback.service.impl.admin;

import com.wanhao.proback.bean.admin.Admin;
import com.wanhao.proback.dao.admin.AdminMapper;
import com.wanhao.proback.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/15 9:32.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    @Transactional(readOnly = true)
    public Admin findByName(String username) {
        //根据姓名查找
        Admin admin = new Admin();
        admin.setUsername(username);
        return adminMapper.selectOne(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> findAll() {
        return adminMapper.selectAll();
    }
}
