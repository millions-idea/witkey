package com.wanhao.proback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 后台项目
 */
@SpringBootApplication
@EnableCaching
public class ProbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProbackApplication.class, args);
    }
}
