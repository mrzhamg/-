package com.test001.demo.mapper;

import com.test001.demo.entity.Cars;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarsMapper {

    //查询购物车全部商品
    public List<Cars> getCarsAll(int uid);

    //插入购物车数据
    public void insertCars(int uid,int gid,int sjid,float price,int num,String dpname,String goods_ms,String imgurl);

    public void deleteCars(int cid);
}
