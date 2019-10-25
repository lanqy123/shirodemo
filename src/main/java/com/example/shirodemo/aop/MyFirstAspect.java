package com.example.shirodemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-24 17:08:33
 **/
@Aspect
@Component
public class MyFirstAspect {

    private static Logger logger= LoggerFactory.getLogger(MyFirstAspect.class);

    @Pointcut("@annotation(com.example.shirodemo.aop.LoginConfig)")
    public void annotationPointcut() {
    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        String value =getAnnotationValue(joinPoint);
        logger.info("beforePointcut  "+value);

    }

    @After("annotationPointcut()")
    public void afterPointcut(JoinPoint joinPoint) {
        String value =getAnnotationValue(joinPoint);
        logger.info("afterPointcut  "+value);
    }
    public String getAnnotationValue(JoinPoint joinPoint){
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LoginConfig annotation = method.getAnnotation(LoginConfig.class);

        String value = annotation.value();
        Boolean isLogin = annotation.needLogin();
        return "注解【"+annotation+"】 value= "+value+"  needLogin= "+isLogin;
    }
}

