package com.test001.demo.controller;

import com.test001.demo.entity.Shangjia_user;
import com.test001.demo.entity.User;
import com.test001.demo.service.RegisterService;
import com.test001.demo.service.Shangjia_userServer;
import com.test001.demo.service.UserService;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private Shangjia_userServer shangjia_userServer;

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        System.out.println(user);
        return registerService.userRegister(user);
    }

    //商家注册
    @PostMapping("/registerStore")
    public Result registerStore(@RequestBody Shangjia_user shangjia_user){
        System.out.println("商家注册=》"+shangjia_user);
        return shangjia_userServer.insertSjUser(shangjia_user);
    }
}
