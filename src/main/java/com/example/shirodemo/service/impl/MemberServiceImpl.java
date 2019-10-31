package com.example.shirodemo.service.impl;

import com.example.shirodemo.annotation.Master;
import com.example.shirodemo.bean.Member;
import com.example.shirodemo.dao.MemberDao;
import com.example.shirodemo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-31 10:12:30
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired(required = false)
    private MemberDao memberdao;

    @Transactional
    @Override
    public int insert(Member member) {
        return memberdao.insert(member);
    }

    @Master
    @Override
    public int save(Member member) {
        return memberdao.insert(member);
    }

    @Override
    public List<Member> selectAll() {
        Member member = new Member();
        member.setName("select");
        memberdao.insert(member);
        return null;
    }

    @Master
    @Override
    public String getToken(String appId) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return null;
    }
}
