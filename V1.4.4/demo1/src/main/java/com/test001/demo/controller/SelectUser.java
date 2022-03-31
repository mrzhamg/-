package com.test001.demo.controller;

import com.test001.demo.service.AddressServer;
import com.test001.demo.service.UserService;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/user")
public class SelectUser {
    private Result result;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressServer addressServer;

    @GetMapping("/selectusername")
    public Result selectUsername(@RequestHeader("Authorization") String token){
//        System.out.println(token);
        return userService.selectUsername(token);
    }
    @RequestMapping("/selectUserAll")
    public Result selectUserAll(@RequestHeader("Authorization") String token){
//        System.out.println(token);
        return userService.selectUserAll(token);
    }
//    @GetMapping("/getAddress")
//    public Result getUserAddress(@RequestHeader("Authorization") String token){
//        return userService.getUserAdd(token);
//    }
}
