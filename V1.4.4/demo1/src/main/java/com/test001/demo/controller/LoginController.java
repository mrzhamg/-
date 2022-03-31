package com.test001.demo.controller;

import com.test001.demo.entity.Shangjia_user;
import com.test001.demo.entity.User;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.service.LoginService;
import com.test001.demo.service.UserService;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//登录接口
@RestController
public class LoginController {
    private Result result;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;

//    账号和密码登录
    @PostMapping("/login")
    public Result login(@RequestBody User user, @RequestHeader("Authorization") String token){
        System.out.println(user+"token=>"+token);
        return loginService.userLogin(user,token);
    }
//手机号获取验证码登录
    @GetMapping("/phoneLogin")
    public Result phoneLogin(@RequestParam String phone){
        System.out.println(phone);
        result=loginService.phoneLogin(phone);
        return result;
    }

    //商家登录
    @PostMapping("/storelogin")
    public Result storelogin(@RequestBody Shangjia_user shangjia_user){
        return loginService.storelogin(shangjia_user);
    }

}
