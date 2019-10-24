package com.example.shirodemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
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

    @Pointcut("@annotation(NeedLogin)")
    public void annotationPointcut() {
    }

//    @Around("annotationPointcut()")
//    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("1");
//        try {
//            joinPoint.proceed();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("4");
//
//    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        String value =getAnnotationValue(joinPoint);
        System.out.println("准备"+value);
    }

    @After("annotationPointcut()")
    public void afterPointcut(JoinPoint joinPoint) {
        String value =getAnnotationValue(joinPoint);
        System.out.println("结束"+value);
    }
    public String getAnnotationValue(JoinPoint joinPoint){
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        NeedLogin annotation = method.getAnnotation(NeedLogin.class);
        String value = annotation.value();
        Boolean isLogin = annotation.isLogin();
        return value+isLogin;
    }
}

