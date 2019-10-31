package com.example.shirodemo.service;

import com.example.shirodemo.annotation.Master;
import com.example.shirodemo.bean.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-31 10:11:58
 **/
public interface MemberService {
    @Transactional
    int insert(Member member);

    @Master
    int save(Member member);

    List<Member> selectAll();

    @Master
    String getToken(String appId);
}
