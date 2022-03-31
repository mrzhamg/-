package com.test001.demo.mapper;

import com.test001.demo.entity.Address;
import com.test001.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressMapper {
    public List<Address> getAddressById(int uid);

    //修改地址
    public void undateAddress(int aid,String addres,String shoujianName,String sjrPhone);

    //添加地址
    public void insertAddress(int aid,int uid,String addres,String shoujianName,String sjrPhone);

    //删除地址
    public void deleteAddress(int aid);
}
