package com.example.shirodemo.controller;

import com.example.shirodemo.bean.Member;
import com.example.shirodemo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-31 10:55:52
 **/
@RestController
@RequestMapping("/member")
public class memberController {
    @Autowired
     private MemberService memberService;

    @RequestMapping("/insert")
    public int insert(HttpServletRequest request){
        Member member = new Member();
        member.setName("总监");
        return memberService.insert(member);
    }
}
