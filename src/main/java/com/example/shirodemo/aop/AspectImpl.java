//package com.example.shirodemo.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
///**
// * description:
// *
// * @author qingyu.lan@ucarinc.com
// * @version 1.0
// * @date: 2019-10-24 15:22:21
// **/
//@Component
//@Aspect
//public class AspectImpl {
//    @Pointcut("@annotation(com.example.shirodemo.aop.NeedLogin)")
//    private void cut() {
//        System.out.println("3");
//    }
//
//// 开始环绕
//
//    @Around("cut()")
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
//
//    @Before("cut()")
//    public void before() {
//        System.out.println("2");
//    }
//
//    @After("cut()")
//    public void after() {
//        System.out.println("5");
//    }
//
//}
