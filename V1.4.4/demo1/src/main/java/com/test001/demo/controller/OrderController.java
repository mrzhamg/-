package com.test001.demo.controller;

import com.test001.demo.entity.Order;
import com.test001.demo.entity.Shangjia_user;
import com.test001.demo.entity.User;
import com.test001.demo.mapper.Shangjia_userMapper;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.service.OrderServer;
import com.test001.demo.util.Result;
import com.test001.demo.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    private Result result;
    private Token token;
    @Autowired
    private OrderServer orderServer;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Shangjia_userMapper shangjia_userMapper;

    @PostMapping("/insertOrder")
    public Result insertOrder(@RequestHeader("Authorization") String t,@RequestBody List<Order> orders){
        System.out.println(orders.size());
        //验证token
        if(token.verify(t)){
            //验证通过,获取用户信息
            for(int i=0;i<orders.size();i++){
                int uid=orders.get(i).getUid();
                int sjid=orders.get(i).getSjid();
                String dpname=orders.get(i).getDpname();
                int gid=orders.get(i).getGid();
                String goods_ms= orders.get(i).getGoods_ms();
                float price= orders.get(i).getPrice();
                int num= orders.get(i).getNum();
                int aid= orders.get(i).getAid();
                String sjr_name= orders.get(i).getSjr_name();
                String sjr_phone= orders.get(i).getSjr_phone();
                String address= orders.get(i).getAddress();
                String imgurl=orders.get(i).getImgurl();


                System.out.println(uid+";"+sjid+";"+dpname+";"+gid+";"+goods_ms+";"+price+";"+num+";"+aid+";"+sjr_name+";"+sjr_phone+";"+address);
                orderServer.insertOrder(uid,sjid,dpname,gid,goods_ms,price,num,aid,sjr_name,sjr_phone,address,imgurl);
            }
            return new Result(200,"成功生成订单",null);
        }else{
            return new Result(400,"订单生成失败",null);
        }
    }

    @GetMapping("/getOrder")
    public List<Order> getOrder(@RequestHeader("Authorization") String t){
//验证token
        if(token.verify(t)){
            //验证通过,获取用户信息
            String username= token.getUsername(t);
            User user=userMapper.selectUsername(username);
            System.out.println(user.getUid());
            return orderServer.getOrder(user.getUid());
        }else{
            return new ArrayList<>();
        }
    }
    //删除订单
    @GetMapping("/deleteOrder")
    public Result deleteOrder(@RequestHeader("Authorization") String t,@RequestParam("oid") int oid){
        if(token.verify(t)){
            //验证通过,获取用户信息
            orderServer.deleteOrder(oid);
            return new Result(200,"订单删除成功",null);
        }else{
            return new Result(400,"身份验证失败，请重新登录再试",null);
        }
    }

    //商家订单管理
    @GetMapping("/getSjOrder")
    public List<Order> getSjOrder(@RequestParam String sjtoken){
        String sjuser = token.getSj_user(sjtoken);
        Shangjia_user shangjia_user = shangjia_userMapper.getSjuser(sjuser);
        int sid = shangjia_user.getSjid();
        return orderServer.getSjOrder(sid);
    }
}
