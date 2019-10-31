package com.example.shirodemo.interceptor;

import com.example.shirodemo.annotation.LoginConfig;
import com.example.shirodemo.exception.NotLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-24 14:14:03
 **/
@Component
public class UserConfig implements HandlerInterceptor {
    private static Logger logger= LoggerFactory.getLogger(UserConfig.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("自定义拦截器");
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            LoginConfig needLogin = ((HandlerMethod)handler).getMethodAnnotation(LoginConfig.class);
            if(needLogin == null || needLogin.needLogin()== false){
                logger.info("您将进入的方法： "+handler+" 不需要登陆");
            }else {
                logger.info("您将进入的方法： "+handler+" 需要登陆");
                try{
                    throw new NotLoginException("自定义异常:请登录");
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
