package com.wanhao.proback.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Created by LiuLiHao on 2018/7/15 11:31.
 * 描述：
 * 作者： LiuLiHao
 */
//@Component("authenticationProvider")
@Service
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(authentication);
        return new UsernamePasswordAuthenticationToken("admin","123");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
