package com.wanhao.proback.security;

import com.wanhao.proback.bean.admin.Admin;
import com.wanhao.proback.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by LiuLiHao on 2018/7/15 9:17.
 * 描述： 权限认证管理
 * 作者： LiuLiHao
 */
@Configuration
@EnableWebSecurity
public class AccessSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminService adminService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .and()
                .formLogin()
                .loginPage("/myAdmin/toLogin")
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable();
    }


//    @Autowired
//    UserDetailsService userDetailsService;
//
//    @Autowired
//    AuthenticationProvider authenticationProvider;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new MyAuthenticationProvider());
//        //auth.userDetailsService(userDetailsService);
//    }


    /**
     * 重写该方法，添加自定义用户
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        Admin admin = adminService.findAll().get(0);
        auth.inMemoryAuthentication()
                .withUser(admin.getUsername()).password(admin.getPassword()).roles("ADMIN")
                .and()
                .withUser("terry").password("terry").roles("USER")
                .and()
                .withUser("larry").password("larry").roles("USER");
    }

}
