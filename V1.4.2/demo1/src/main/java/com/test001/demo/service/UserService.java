package com.test001.demo.service;

import com.test001.demo.entity.User;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.util.Result;
import com.test001.demo.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/* 用户的增删改查
*  **/
@Service
public class UserService {
    private Result result;
    private Token token;

    @Autowired
    public UserMapper userMapper;

    public Result getUserAdd(String t){
        if(token.verify(t)){
            //解析token
            String username=token.getUsername(t);
            User user=userMapper.selectUsername(username);
            System.out.println(userMapper.getUserAdd(user.getUid()));
            Map data=new HashMap();
            data.put("user",userMapper.getUserAdd(user.getUid()));
            result=new Result(200,"获取用户信息成功",data);
            return result;
        }else{
            return  new Result(500,"登录过期，请重新登录",null);
        }

    }

    public Result selectUsername(String t){
//        System.out.println(t);
        if(token.verify(t)){
            String username=token.getUsername(t);
            User username1=userMapper.selectUsername(username);
            if(username1==null){
                result = new Result(500, "获取用户信息失败", null);
                return result;
            }else {
                username1.setPassword(null);
                Map m1 = new HashMap();
                m1.put("user", username1);
                result = new Result(200, "成功获取用户信息", m1);
                return result;
            }
        }else{
            result=new Result(500,"身份认证已过期，请重新登录",null);
            return result;
        }
    }

//    查询所有用户
    public Result selectUserAll(String t){
        if(t==null){
            result=new Result(400,"未登录，请登录后再试",null);
            return result;
        }else{
            //验证token是否过期
            Token token=new Token();
            Boolean istoken=token.verify(t);
            if(istoken){
                List userList= new ArrayList();
                userList=userMapper.findAll();
                Map<String,Object> map=new HashMap<>();
                map.put("user",userList);
                result=new Result(200,"操作成功！",map);
                return result;
            }else{
                result=new Result(400,"登录身份已过期，请重新登录再试",null);
                return result;
            }
        }

    }

    //修改用户信息
    public Result updateUsre(String t,String username,String password,String name,int sex,int age,String phone) {
        //验证手机号是否已注册
        User phoneUser = userMapper.selectPhoneAndUsername(phone,username);
        //验证昵称是否被占用
        User isName=userMapper.selectNameAndUsername(name,username);
        if (t == null) {
            result = new Result(500, "用户未登录，请登录再试", null);
            return result;
        } else if (username == null) {
            result = new Result(500, "账号不能为空！", null);
            return result;
        } else if (password == null) {
            result = new Result(500, "密码不能为空", null);
            return result;
        } else if (name == null) {
            result = new Result(500, "昵称不能为空", null);
            return result;
        } else if ("0".equals(String.valueOf(age)) || age<0|| age >150) {
            result = new Result(500, "请输入正确年龄", null);
            return result;
        } else if (phone == null) {
            result = new Result(500, "手机号不能为空", null);
            return result;
        } else {

            if(isName!=null){
                result = new Result(500, "该昵称已被占用", null);
                return result;
            }else if (phoneUser != null) {
                    result = new Result(500, "该手机号已被绑定", null);
                    return result;
                } else {
                    if (token.verify(t)) {
                        //token验证通过，可修改
                        Boolean isUp = userMapper.updateUsre(username, password, name, sex, age, phone);
                        System.out.println("是否修改成功=》" + isUp);
                        if (isUp) {
                            result = new Result(200, "修改成功，请重新登录", null);

                            return result;
                        } else {
                            result = new Result(500, "修改失败", null);
                            return result;
                        }

                    } else {
                        //token验证不通过
                        result = new Result(500, "token验证失败，或身份认证已过期，请重新登录再试...", null);
                        return result;
                    }
                }
        }
    }
}
