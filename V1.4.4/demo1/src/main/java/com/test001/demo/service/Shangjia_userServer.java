package com.test001.demo.service;

import com.test001.demo.entity.Shangjia_user;
import com.test001.demo.mapper.Shangjia_userMapper;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.util.Result;
import com.test001.demo.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Shangjia_userServer {
    private Result result;
    private Token token;
    @Autowired
    private Shangjia_userMapper shangjia_userMapper;

    @Autowired
    private UserMapper userMapper;

    public Result insertSjUser(Shangjia_user shangjia_user){
        String user = shangjia_user.getSj_user();
        String password = shangjia_user.getSj_password();
        String name = shangjia_user.getSj_name();
        String phone = shangjia_user.getSj_phone();
        System.out.println("商家注册=>"+user+";"+password+";"+name);
        //判断用户是否已注册
        if(shangjia_userMapper.getSjuser(user)!=null){
            return new Result(500,"该用户已被注册",null);
        }else if(shangjia_userMapper.getSjname(name)!=null){
            return new Result(500,"该店铺名称已被注册",null);
        }else if(shangjia_userMapper.getSjphone(phone)!=null){
            return new Result(500,"该手机号已被注册",null);
        }else{
            //判断用户表手机号与商家表手机号
            if(userMapper.selectPhone(phone)!=null){
                userMapper.updataIsStore(phone,true);
            }else{
                return new Result(200,"该手机号尚未未注册用户端账号，建议注册用户端账号！",null);
            }
            shangjia_userMapper.insertSjUser(user,password,name,phone);
            return new Result(200,"商家注册成功！",null);
        }
    }
}
