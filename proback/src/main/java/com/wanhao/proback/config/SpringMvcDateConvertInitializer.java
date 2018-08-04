/***
 * @pName proback
 * @name SpringMvcDateConvertor
 * @user HongWei
 * @date 2018/8/4
 * @desc
 */
package com.wanhao.proback.config;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SpringMvcDateConvertInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder webDataBinder) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8")); // 设置时区为GMT  +8为北京时间东八区

        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        webDataBinder.registerCustomEditor(Timestamp.class, new CustomDateEditor(sdf, true));
    }
}
