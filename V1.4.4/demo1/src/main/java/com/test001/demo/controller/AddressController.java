package com.test001.demo.controller;

import com.test001.demo.entity.Address;
import com.test001.demo.entity.User;
import com.test001.demo.mapper.AddressMapper;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.service.AddressServer;
import com.test001.demo.util.Result;
import com.test001.demo.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddressController {
    private Token token;
    @Autowired
    private AddressServer addressServer;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getAddress")
    public Result getAddress(@RequestHeader("Authorization") String t,@RequestParam int uid){
        System.out.println("uid=>"+uid);
        //验证token
        if(token.verify(t)){
            return addressServer.getUserAddress(uid);
        }else{
            return new Result(500,"获取地址失败",null);
        }
    }
    @GetMapping("/getCarAddress")
    public List<Address> getCarAddress(@RequestHeader("Authorization") String t){
        //验证token
        if(token.verify(t)){
            //验证通过,获取用户信息
            String username= token.getUsername(t);
            User user=userMapper.selectUsername(username);
            System.out.println(user.getUid());
            return addressMapper.getAddressById(user.getUid());
        }else{
            return new ArrayList<>();
        }
    }

    @PostMapping("/undateAddress")
    public Result undateAddress(@RequestHeader("Authorization") String token, @RequestBody Address address){
//        System.out.println(address);
        return addressServer.undateAddress(token,address);
    }
    @PostMapping("/insertAddress")
    public Result insertAddress(@RequestHeader("Authorization") String token, @RequestBody Address address){
        return addressServer.insertAddress(token,address);
    }
    @GetMapping("/deleteAddress")
    public Result deleteAddress(@RequestHeader("Authorization") String token,@RequestParam int aid){
        return addressServer.deleteAddress(token,aid);
    }
}
