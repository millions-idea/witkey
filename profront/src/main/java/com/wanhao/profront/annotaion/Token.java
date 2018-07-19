package com.wanhao.profront.annotaion;

import java.lang.annotation.*;

/**
 * Created by LiuLiHao on 2018/7/19 23:46.
 * 描述： 防止表单重复提交
 * 作者： LiuLiHao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Token {

    //生成 Token 标志
    boolean save() default false ;
    //移除 Token 值
    boolean remove() default false ;
}
