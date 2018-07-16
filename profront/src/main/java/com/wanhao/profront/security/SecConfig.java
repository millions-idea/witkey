package com.wanhao.profront.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .and()
                .formLogin()
                .and().authorizeRequests().anyRequest().permitAll()
                .and().formLogin()
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
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("terry").password("terry").roles("USER")
                .and()
                .withUser("larry").password("larry").roles("USER");
    }
}