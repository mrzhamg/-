package com.test001.demo.controller;

import com.test001.demo.entity.User;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.service.UserService;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Result result;
    @Autowired
    private UserService userService;
    private UserMapper userMapper;


//修改用户信息
    @GetMapping ("/updateUsre")
    public Result updateUsre(@RequestHeader("Authorization") String token,@RequestParam String username,@RequestParam String password,@RequestParam String name,@RequestParam int sex,@RequestParam int age,@RequestParam String phone){
        System.out.println("token=>"+token+"\n"+username+","+password+","+name+","+sex+","+age+","+phone);
//        result=new Result(200,"修改成功",null);
        return userService.updateUsre(token,username,password,name,sex,age,phone);
    }




//    @RequestMapping("/XueHao")
//    public User getXueHao(){
////        System.out.println(name);
//        return userService.selectByXueHao(222222);
//    }
//
//    @RequestMapping("/insert")
//    public String insert(User u){
//        userService.insert(u);
//        return "success";
//    }


}
