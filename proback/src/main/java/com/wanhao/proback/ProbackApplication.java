package com.wanhao.proback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * 后台项目
 */
@SpringBootApplication
@EnableCaching
public class ProbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProbackApplication.class, args);
    }


    //设置ajax跨域请求
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //设置上传文件大小限制
        factory.setMaxFileSize("5MB");
        //设置上传总数据大小
        factory.setMaxRequestSize("5MB");
        return factory.createMultipartConfig();
    }

}
