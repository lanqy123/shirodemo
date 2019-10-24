package com.example.shirodemo.aop;

import java.lang.annotation.*;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-24 15:14:18
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NeedLogin {
    String value() default "";

    boolean isLogin() default false;
}
