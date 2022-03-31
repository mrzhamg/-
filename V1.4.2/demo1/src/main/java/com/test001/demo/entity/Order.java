package com.test001.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private int oid;
    private int uid;
    private int sjid;
    private String dpname;
    private int gid;
    private String goods_ms;
    private float price;
    private int num;
    private int aid;
    private String sjr_name;
    private String sjr_phone;
    private String address;
    private String imgurl;
    private String date;

    public User user;

    public Order(){
        user=new User();
    }

}
