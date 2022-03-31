package com.test001.demo.mapper;

import com.test001.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    public User userLogin(String username, String password);

    //根据电话查询用户
    public User selectPhone(String phone);
}
