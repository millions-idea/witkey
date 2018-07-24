package com.wanhao.proback.security;

import com.wanhao.proback.utils.Base64Utils;
import com.wanhao.proback.utils.Constants;
import com.wanhao.proback.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by LiuLiHao on 2018/7/15 9:17.
 * 描述： 权限认证管理
 * 作者： LiuLiHao
 */
@Configuration
@EnableWebSecurity
public class AccessSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/myAdmin/toLogin")           // 设置登录页面
                .successForwardUrl("/myAdmin/systemIndex")
                .failureUrl("/myAdmin/toLogin?error=true") //登录失败跳转到的页面
                .loginProcessingUrl("/myAdmin/login")  // 自定义的登录接口
                .and()
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/myAdmin/toLogin","/intf_member/**","intf_member/**").permitAll()     // 设置所有人都可以访问登录页面
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable();          // 关闭csrf防护
    }


    @Autowired
    UserDetailsService userDetailsService;

    /**
     * 重写该方法，添加自定义用户
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        byte[] decode;
                        try {
                            decode = Base64Utils.decode(charSequence.toString());
                            byte[] content = RSAUtil.decryptByPrivateKey(decode, Constants.PRI_KEY);
                            //解密结果

                            return new String(content);

                        } catch (Exception e) {
                            //System.out.println("异常");
                        }

                        return null;
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        byte[] decode;
                        try {
                            decode = Base64Utils.decode(charSequence.toString());
                            byte[] content = RSAUtil.decryptByPrivateKey(decode, Constants.PRI_KEY);
                            //解密结果
                            String s1 = new String(content);
                            return s1.equals(s);

                        } catch (Exception e) {
                            //System.out.println("异常");
                        }
                        return false;
                    }
                });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/dist/**")
                .antMatchers("/js/**")
                .antMatchers("/img/**")
                //前台使用
                .antMatchers("/kaptcha/**")
                .antMatchers("/area/**")
                .antMatchers("/fileup/**")
                .antMatchers("/intf_member/**")
                .antMatchers("/images/**")
                .antMatchers("/intf_setting/**")
                .antMatchers("/intf_vip/**")
                .antMatchers("/intf_shop/**")

        ;
    }


}
