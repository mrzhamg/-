package com.test001.demo.service;

import com.test001.demo.entity.UserImg;
import com.test001.demo.mapper.UserImgMapper;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserImgService {

    @Autowired
    UserImgMapper userImgMapper;

    public Result PicpathService(UserImg userImg) {
        System.out.println("userImg=>"+userImg);
        int uid=userImg.getUid();
        if(this.getPic(uid)==null){
            userImgMapper.add(userImg);
//            System.out.println("int=>"+userImgMapper.update(userImg));
            return new Result(200,"头像上传成功",null);
        }else{
            userImgMapper.update(userImg);
//            System.out.println("int=>"+userImgMapper.update(userImg));
            return new Result(200,"头像修改成功",null);
        }

    }

    public UserImg getPic(int uid){
        return userImgMapper.getpic(uid);
    }

}
