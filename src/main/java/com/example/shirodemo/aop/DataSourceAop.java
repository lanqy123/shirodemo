package com.example.shirodemo.aop;

import com.example.shirodemo.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-31 10:07:01
 **/
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.example.shirodemo.annotation.Master) " +
            "&& (execution(* com.example.shirodemo.service..*.select*(..)) " +
            "|| execution(* com.example.shirodemo.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.example.shirodemo.annotation.Master) " +
            "|| execution(* com.example.shirodemo.service..*.insert*(..)) " +
            "|| execution(* com.example.shirodemo.service..*.add*(..)) " +
            "|| execution(* com.example.shirodemo.service..*.update*(..)) " +
            "|| execution(* com.example.shirodemo.service..*.edit*(..)) " +
            "|| execution(* com.example.shirodemo.service..*.delete*(..)) " +
            "|| execution(* com.example.shirodemo.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
