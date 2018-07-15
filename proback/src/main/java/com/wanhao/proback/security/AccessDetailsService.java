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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by LiuLiHao on 2018/7/15 9:19.
 * 描述：
 * 作者： LiuLiHao
 */
@Component("userDetailsService")
public class AccessDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户的用户名: {}", username);

        //查找对应admin
        Admin admin = adminService.findByName(username);

        String password = passwordEncoder.encode(admin.getPassword());
        logger.info("password: {}", password);

        // 参数分别是：用户名，密码，用户权限
        User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMI"));
        return user;
    }
}
