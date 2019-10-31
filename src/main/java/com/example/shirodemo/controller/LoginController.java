package com.example.shirodemo.controller;


import javax.servlet.http.HttpServletRequest;

import com.example.shirodemo.annotation.LoginConfig;
import com.example.shirodemo.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-22 09:49:58
 **/
@Controller
public class LoginController {


    private static Logger logger=LoggerFactory.getLogger(LoginController.class);

    @LoginConfig(value = "loginPost")
    @PostMapping("login")
    public ModelAndView login(User user, RedirectAttributes redirectAttributes, boolean rememberMe) {
        ModelAndView view =new ModelAndView();
        String userName = user.getUsername();
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()) {
            UsernamePasswordToken token =new UsernamePasswordToken(userName,user.getPassword());
            try {
                if(rememberMe) {
                    token.setRememberMe(true);
                }
                currentUser.login(token);


                view.setViewName("redirect:/");
            }catch (UnknownAccountException uae) {
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
                redirectAttributes.addFlashAttribute("message", "未知账户");
            } catch (IncorrectCredentialsException ice) {
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
                redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
            } catch (LockedAccountException lae) {
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
                redirectAttributes.addFlashAttribute("message", "账户已锁定");
            } catch (ExcessiveAttemptsException eae) {
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
                redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
            } catch (AuthenticationException ae) {
                //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
                ae.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
            }
        }

        view.setViewName("redirect:/login");
        return view;

    }

    @LoginConfig(value = "loginGet")
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                return "redirect:/";
            } else {
                logger.info("--进行登录验证..验证开始");
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login";
    }
    @LoginConfig(value = "su",needLogin = true)
    @RequestMapping("su")
    public ModelAndView su() {
        ModelAndView view =new ModelAndView("index");
        System.out.println("来到了su方法。。。");

        return view;
    }


    @LoginConfig(value = "xiao",needLogin = true)
    @RequiresRoles({"admin"})
    @RequestMapping("xiao")
    public ModelAndView xiao() {
        ModelAndView view =new ModelAndView("index");
        System.out.println("来到了xiao方法。。。");

        return view;
    }
}
