package com.wanhao.profront.security;

import com.wanhao.profront.utils.Base64Utils;
import com.wanhao.profront.utils.Constants;
import com.wanhao.profront.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//            http.formLogin()
//                    .loginPage("/member/login")           // 设置登录页面
//                    .successForwardUrl("/member/systemIndex")
//                    .failureUrl("/member/login?error=true") //登录失败跳转到的页面
//                    .defaultSuccessUrl("/member/systemIndex")
//
//                    //.loginProcessingUrl("/member/login")  // 自定义的登录接口
//
//                    .and()
//                    .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
//                    .antMatchers("/member/login").permitAll()     // 设置所有人都可以访问登录页面
//                    .anyRequest()               // 任何请求,登录后可以访问
//                    .authenticated()
//                    .and()
//                    .csrf().disable().rememberMe();          // 关闭csrf防护

//        http.authorizeRequests().
//                antMatchers("/**","/fileup/**")
//                .permitAll();

        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/member/toLogin")           // 设置登录页面
                .successForwardUrl("/member/systemIndex")
                .failureUrl("/member/toLogin?error=true") //登录失败跳转到的页面
                .loginProcessingUrl("/member/login")  // 自定义的登录接口
                .and()
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/member/toLogin").permitAll()     // 设置所有人都可以访问登录页面
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable();

    }

    @Autowired
    UserDetailsService userDetailsService;

    /**
     * 重写该方法，添加自定义用户
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)

                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        byte[] decode;
                        try {
                            int i;
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
                            int i;
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

        ;
    }

}