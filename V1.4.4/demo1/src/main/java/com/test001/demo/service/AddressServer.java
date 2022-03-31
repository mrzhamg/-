package com.test001.demo.service;

import com.test001.demo.entity.Address;
import com.test001.demo.mapper.AddressMapper;
import com.test001.demo.util.Result;
import com.test001.demo.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServer {
    private Result result;
    private Token token;
    @Autowired
    private AddressMapper addressMapper;

    public Result getUserAddress(int uid){
        System.out.println(addressMapper.getAddressById(uid));
        List address=addressMapper.getAddressById(uid);
        Map data=new HashMap();
        data.put("address",address);
        result=new Result(200,"操作成功",data);
        return result;
    }
    //修改地址
    //int aid,String addres,String shoujianName,String sjrPhone
    public Result undateAddress(String t,Address address){
//        System.out.println("token=>"+t+";address=>"+address);
        int aid=address.getAid();
        String addres=address.getAddres();
        String shoujianName=address.getShoujianName();
        String sjrPhone=address.getSjrPhone();
        //验证token
        if(token.verify(t)){
            //验证通过
            addressMapper.undateAddress(aid,addres,shoujianName,sjrPhone);
            result=new Result(200,"修改成功",null);
            return result;
        }else{
            result=new Result(500,"修改失败",null);
            return result;
        }
    }
    //添加地址
    public Result insertAddress(String t,Address address){
        System.out.println("token=>"+t+";address=>"+address);
        int aid=address.getAid();
        int uid=address.getUid();
        String addres=address.getAddres();
        String shoujianName=address.getShoujianName();
        String sjrPhone=address.getSjrPhone();
        //验证token
        //验证token
        if(token.verify(t)){
            //验证通过
            addressMapper.insertAddress(aid,uid,addres,shoujianName,sjrPhone);
            result=new Result(200,"添加成功",null);
            return result;
        }else{
            result=new Result(500,"添加失败",null);
            return result;
        }

    }

    //删除地址
    public Result deleteAddress(String t,int aid){
        if(token.verify(t)){
            //验证通过
            addressMapper.deleteAddress(aid);
            result=new Result(200,"删除成功",null);
            return result;
        }else{
            result=new Result(500,"删除失败",null);
            return result;
        }
    }
}
