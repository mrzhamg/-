package com.test001.demo.mapper;

import com.test001.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //获取所有用户
    public List<User> findAll();
    //查询是否是已注册的用户
    public User selectUsername(String username);

    //查询昵称
    public User selectName(String name);

    //查询昵称和账号
    public User selectNameAndUsername(String name,String username);
    //查询电话和账号
    public User selectPhoneAndUsername(String phone,String username);
    //根据电话查询用户
    public User selectPhone(String phone);
    //注册用户
    public void insertUser(int uid,String username,String password,String name,int sex,int age,String phone,String date,Boolean isStore);
    //修改用户信息
    public Boolean updateUsre(String username,String password,String name,int sex,int age,String phone);
    //注销（删除）用户
    public void delete(int xh);

    public Boolean updataIsStore(String phone,Boolean isStore);

    public User getUserAdd(int uid);

}
