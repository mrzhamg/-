package com.test001.demo.controller;

import com.test001.demo.entity.Cars;
import com.test001.demo.entity.User;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.service.CarsService;
import com.test001.demo.service.UserService;
import com.test001.demo.util.Result;
import com.test001.demo.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarsController {
    private Result result;
    private Token token;

    @Autowired
    private CarsService carsService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getCars")
    public List<Cars> getCarsAll(@RequestHeader("Authorization") String t){
        //验证token
        if(token.verify(t)){
            //验证通过,获取用户信息
            String username= token.getUsername(t);
            User user=userMapper.selectUsername(username);
            System.out.println(user.getUid());
            return carsService.getCarsAll(user.getUid());
        }else{
            return new ArrayList<>();
        }
    }

    @PostMapping("/insertCars")
    public Result insertCars(@RequestHeader("Authorization") String t, @RequestBody Cars cars){
//        System.out.println("cars=>"+cars);
        //验证token
        if(token.verify(t)){
            //验证通过,获取用户信息
            String username= token.getUsername(t);
            User user=userMapper.selectUsername(username);
            int uid=user.getUid();
            int gid=cars.getGid();
            int sjid=cars.getSjid();
            Float price=cars.getPrice();
            int num=cars.getNum();
            String dpname=cars.getDpname();
            String goods_ms=cars.getGoods_ms();
            String imgurl=cars.getImgurl();
            carsService.insertCars(uid,gid,sjid,price,num,dpname,goods_ms,imgurl);
            return new Result(200,"该商品已加入购物车了",null);
        }else{
            return new Result(500,"身份验证失败,请重新登录",null);
        }
    }

    @GetMapping("/deleteCars")
    public Result deleteCars(@RequestHeader("Authorization") String t,@RequestParam int cid){
        //验证token
        if(token.verify(t)){
            //验证通过,获取用户信息
            carsService.deleteCars(cid);
            return new Result(200,"该商品已从购物车中删除",null);
        }else{
            return new Result(500,"删除失败",null);
        }

    }
}
