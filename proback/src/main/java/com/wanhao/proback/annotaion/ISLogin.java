package com.wanhao.proback.annotaion;

import java.lang.annotation.*;

/**
 * Created by LiuLiHao on 2018/7/21 10:48.
 * 描述： 判断是否已经登录 是否在另一个地点登录
 * 作者： LiuLiHao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ISLogin {

}
