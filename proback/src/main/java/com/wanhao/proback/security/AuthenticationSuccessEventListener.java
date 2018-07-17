package com.wanhao.proback.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Created by LiuLiHao on 2018/7/17 9:45.
 * 描述：
 * 作者： LiuLiHao
 */
public class AuthenticationSuccessEventListener  implements ApplicationListener<AuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails)
                e.getAuthentication().getDetails();

        //loginAttemptService.loginSucceeded(auth.getRemoteAddress());
    }
}
