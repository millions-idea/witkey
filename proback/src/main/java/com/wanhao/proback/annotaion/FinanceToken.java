/***
 * @pName proback
 * @name FinanceToken
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 财务模块专用验签
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FinanceToken {

}
