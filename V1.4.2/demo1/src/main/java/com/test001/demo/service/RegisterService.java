package com.test001.demo.service;

import com.test001.demo.entity.User;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RegisterService {

    private Result result;
    @Autowired
    private UserMapper userMapper;

    //注册用户(先查询用户是否存在)
    public Result userRegister(User user){
//        System.out.println("注册=>"+user);
        int uid=user.getUid();
        String username=user.getUsername();
        String password=user.getPassword();
        String name=user.getName();
        int sex=user.getSex();
        int age=user.getAge();
        String telphone=user.getPhone();
//判断前端发送的注册请求数据是否合法
        if(username==null){
            result=new Result(500,"账号不能为空！",null);
            return result;
        }else if(password==null){
            result=new Result(500,"密码不能为空！",null);
            return result;
        }else if(name==null){
            result=new Result(500,"昵称不能为空！",null);
            return result;
        }else if(telphone==null){
            result=new Result(500,"手机号不能为空！",null);
            return result;
        }else {
            //根据用户名查询该用户是否存在
            if(userMapper.selectUsername(username)!=null){
                result=new Result(500,"该账号已存在！请登录...",null);
                return result;
            }else if(userMapper.selectPhone(telphone)!=null){
                //注册
                result=new Result(500,"该手机号已被注册！请登录...",null);
                return result;
            }else{
                //生成注册时间
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy'年'MM'月'dd'日' HH:mm:ss");
                Date dates = new Date(System.currentTimeMillis());
                String dateStr=formatter.format(dates);
                //默认不是商家
                Boolean isStore=false;
//            user.setDate(dateStr);
                //调用插入数据方法
                userMapper.insertUser(uid,username,password,name,sex,age,telphone,dateStr,isStore);
//            System.out.println(user);
                result=new Result(200,"注册成功！请登录...",null);
                return result;
            }
        }
    }
}
