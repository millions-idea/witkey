/***
 * @pName proback
 * @name SpringMvcDateConvertConfiguration
 * @user HongWei
 * @date 2018/8/4
 * @desc
 */
package com.wanhao.proback.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class SpringMvcDateConvertConfiguration {

    @Autowired
    public void setWebBindingInitializer(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        requestMappingHandlerAdapter.setWebBindingInitializer(new SpringMvcDateConvertInitializer());
    }
}
