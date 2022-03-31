package com.test001.demo.service;

import com.test001.demo.entity.Order;
import com.test001.demo.mapper.OrderMapper;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServer {

    @Autowired
    private OrderMapper orderMapper;

    public void insertOrder(int uid,int sjid,String dpname,int gid,String goods_ms,float price,int num,int aid,String sjr_name,String sjr_phone,String address,String imgurl){
        //生成注册时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy'年'MM'月'dd'日' HH:mm:ss");
        Date dates = new Date(System.currentTimeMillis());
        String date=formatter.format(dates);
        orderMapper.insertOrder(uid,sjid,dpname,gid,goods_ms,price,num,aid,sjr_name,sjr_phone,address,imgurl,date);
        //System.out.println(uid+";"+sjid+";"+dpname+";"+gid+";"+goods_ms+";"+price+";"+num+";"+aid+";"+sjr_name+";"+sjr_phone+";"+address);
    }

    public List<Order> getOrder(int uid){
        System.out.println("uid=>"+uid);
        return orderMapper.getOrder(uid);
    }

    public List<Order> getSjOrder(int sjid){
        System.out.println("Sjid=>"+sjid);
        return orderMapper.getSjOrder(sjid);
    }

    public void deleteOrder(int oid){
        orderMapper.deleteOrder(oid);
    }
}
