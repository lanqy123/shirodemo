package com.example.shirodemo.dao;
import com.example.shirodemo.bean.User;
import org.apache.ibatis.annotations.Mapper;
/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-22 09:41:30
 **/



@Mapper
public interface UserDao {

    User findByUserName(String username);

}