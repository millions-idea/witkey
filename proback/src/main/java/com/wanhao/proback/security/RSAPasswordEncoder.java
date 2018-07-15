package com.wanhao.proback.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by LiuLiHao on 2018/7/15 9:45.
 * 描述： rsa 加密
 * 作者： LiuLiHao
 */
@Component("passwordEncoder")
public class RSAPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        System.out.println(charSequence);
        //BCryptPasswordEncoder
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.println(charSequence);
        System.out.println(s);
        return false;
    }
}
