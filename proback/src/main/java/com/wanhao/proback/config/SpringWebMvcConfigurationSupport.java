/***
 * @pName proback
 * @name SpringMvcConfigurerAdapter
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.config;

import com.wanhao.proback.interceptor.FinanceAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringWebMvcConfigurationSupport extends WebMvcConfigurationSupport {


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 财务模块
        registry.addInterceptor(FinanceAuthenticationInterceptor()).addPathPatterns("/finance/**");
        super.addInterceptors(registry);
    }


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public FinanceAuthenticationInterceptor FinanceAuthenticationInterceptor() {
        return new FinanceAuthenticationInterceptor();
    }

}
