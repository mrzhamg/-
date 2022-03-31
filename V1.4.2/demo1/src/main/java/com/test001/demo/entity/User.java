package com.test001.demo.entity;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private int uid;
    private String username;
    private String password;
    private String name;
    private int sex;
    private int age;
    private String phone;
    private String date;
    private Boolean isStore;
    private List<Address> address;//一个用户可有多个收货地址

    public User(){
        address=new ArrayList<>();
    }
}
