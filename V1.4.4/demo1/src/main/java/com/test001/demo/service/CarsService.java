package com.test001.demo.service;

import com.test001.demo.entity.Cars;
import com.test001.demo.mapper.CarsMapper;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsService {
    @Autowired
    private CarsMapper carsMapper;

    public List<Cars> getCarsAll(int uid){
        return carsMapper.getCarsAll(uid);
    };

    public void insertCars(int uid, int gid, int sjid, float price, int num, String dpname, String goods_ms, String imgurl){
//        System.out.println("uid=>"+uid+";gid=>"+gid+";sjid=>"+sjid+";price=>"+price+";num=>"+num+";dpname=>"+dpname+";goods_ms=>"+goods_ms+";imgurl=>"+imgurl);
        carsMapper.insertCars(uid,gid,sjid,price,num,dpname,goods_ms,imgurl);
    }

    public void deleteCars(int cid){
        carsMapper.deleteCars(cid);
    };
}
