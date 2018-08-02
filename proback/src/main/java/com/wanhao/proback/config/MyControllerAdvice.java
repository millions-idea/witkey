package com.wanhao.proback.config;

import com.wanhao.proback.exception.TokenInvalidException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuLiHao on 2018/7/29 14:13.
 * 描述： 全局异常处理
 * 作者： LiuLiHao
 */
@ControllerAdvice
public class MyControllerAdvice {
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        System.err.println(ex);
        Map map = new HashMap();
        map.put("error", 1);
        map.put("message", "错误");
        return map;
    }

    /**
     * 拦截捕捉自定义异常 token错误
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = TokenInvalidException.class)
    public Map myErrorHandler(TokenInvalidException ex) {
        Map map = new HashMap();
        map.put("error", 1);
        map.put("message", "登录过期,请从新登录");
        return map;
    }



}
