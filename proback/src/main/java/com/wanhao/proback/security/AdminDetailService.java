package com.wanhao.proback.security;

import com.wanhao.proback.bean.admin.Admin;
import com.wanhao.proback.service.admin.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by LiuLiHao on 2018/7/16 22:07.
 * 描述：
 * 作者： LiuLiHao
 */
@Component(value = "userDetailsService")
public class AdminDetailService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findByName(username);
        if(admin == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        User user = new User(username, admin.getPassword(),
        AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }


}
