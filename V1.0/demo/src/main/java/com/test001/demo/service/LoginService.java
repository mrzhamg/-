package com.test001.demo.service;

import com.test001.demo.entity.User;
import com.test001.demo.mapper.LoginMapper;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.util.Result;
import com.test001.demo.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private Result result;

    @Autowired
    private LoginMapper loginMapper;

    //登录业务
    public Result userLogin(User user,String t){
        String usrename=user.getUsername();
        String password=user.getPassword();

        if(usrename==null){
            result = new Result(400, "用户名不能为空！", null);
            return result;
        }else if(password==null){
            result = new Result(400, "密码不能为空！", null);
            return result;
        }else {
            User user1 = loginMapper.userLogin(usrename, password);
//            System.out.println("前端发送的请求=>" + user);
            //查询用户不存在时user为空，存在时返回用户对象
            if (user1 == null) {
                result = new Result(400, "用户名或密码错误！", null);
                return result;
            } else {
//                System.out.println("用户登录=》" + user1.getUsername());
                Token token = new Token();
                String token1 = token.sign(user1);//调用生成token方法
                //new一个Map集合，把token字符串和用户名存入集合中
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("token", token1);
                dataMap.put("username", user1.getUsername());

                Boolean istoken = token.verify(token1);//调用验证token方法
                System.out.println("token" + istoken);

                result = new Result(200, "登录成功！", dataMap);
                return result;
            }
        }
    }

    public Result phoneLogin(String phone){
        //查询该手机号是否已被注册
        System.out.println(loginMapper.selectPhone(phone));
        if(loginMapper.selectPhone(phone)!=null){
            result=new Result(200,"手机验证码模块未完善",null);
            return result;
        }else{
            result=new Result(500,"验证码发送失败，该手机号未注册，请前往注册",null);
            return result;
        }

    }

}
