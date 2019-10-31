package com.example.shirodemo.dao;

import com.example.shirodemo.bean.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-31 10:16:51
 **/
@Mapper
public interface MemberDao {

    int insert(Member member);

    List<Member> selectAll();
}
