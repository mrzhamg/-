package com.test001.demo.controller;

import com.test001.demo.entity.User;
import com.test001.demo.entity.UserImg;
import com.test001.demo.mapper.UserMapper;
import com.test001.demo.service.UserImgService;
import com.test001.demo.service.UserService;
import com.test001.demo.util.Result;
import com.test001.demo.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class UserImgController {
    private Token token;
    private Result result;
    @Autowired
    private UserImgService userImgService;
    @Autowired
    private UserMapper userMapper;

    //保存用户头像
    @PostMapping(value = "/postpicpath")
    private Result upLoadName(@RequestParam MultipartFile file,@RequestHeader("Authorization") String t) throws IOException {
        System.out.println("token=>"+t+";uid=>"+";file=>"+file);
        //验证token
        File fileMY_data = new File("C:\\MiYouStore_data");
        File isfile = new File("C:\\MiYouStore_data\\userImg");
        //如果文件夹不存在则创建
        if (!fileMY_data.exists() && !fileMY_data.isDirectory()) {
            fileMY_data.mkdir();
            return new Result(500, "经检测该路径不存在，已自动创建MiYouStore_data文件夹，请重新上传", null);
        } else if(!isfile.exists() && !isfile.isDirectory()){
            isfile.mkdir();
            return new Result(500, "经检测该路径不存在，已自动创建MiYouStore_data/userImg文件夹，请重新上传", null);
        }else{
            if(token.verify(t)){
                String username = token.getUsername(t);
                User user1 = userMapper.selectUsername(username);
                int uid=user1.getUid();
                String name =file.getOriginalFilename();
                String type = name.substring(name.lastIndexOf("."));
                UUID uuid = UUID.randomUUID();
                File directory = new File("src/main/resources/static/");
                String paths = directory.getCanonicalPath();
//        System.out.println("path=>"+paths);
                FileOutputStream fileOutputStream = new FileOutputStream("C:\\MiYouStore_data\\userImg\\" + uuid.toString()+ type);
//            FileOutputStream fileOutputStream = new FileOutputStream(paths+"\\images\\userimg\\" + uuid.toString()+ type);
                fileOutputStream.write(file.getBytes());//写入硬盘
        System.out.println("图片字节流=》"+file);
                fileOutputStream.close();//关闭字节流
                UserImg picpath = new UserImg();
                picpath.setUid(uid);
                picpath.setName(name);
                picpath.setBid("/MiYouStore_data/userImg/"+uuid.toString()+type);
                userImgService.PicpathService(picpath);
                return new Result(200,"上传成功",null);
            }else{
                return new Result(500,"身份验证失败，请重新登录再试",null);
            }
        }



    }

//    获取用户头像
//    @GetMapping(value = "/getpic" ,produces =MediaType.IMAGE_PNG_VALUE)
//    @ResponseBody
//    public byte[] getPic(@RequestParam int uid) throws IOException{
//        //传入id获取图片路径
//        UserImg userImg = userImgService.getPic(uid);
//        String name = userImg.getName();
//        String type = name.substring(name.lastIndexOf("."));
//        String bid = userImg.getBid();
//        File file=new File("d:\\MiYouStore_data\\userImg\\" + bid + type);
//        FileInputStream picInput = new FileInputStream(file);
//        System.out.println("uuid=>"+bid+";type=>"+type+"; 图片字节流=>");
//        return picInput.readAllBytes();
//    }

    @GetMapping("/getpic")
    public UserImg getUserimg(@RequestHeader("Authorization") String t){
        //判断用户是否登录成功
        if(token.verify(t)){
            //解析token获取用户id
            String username=token.getUsername(t);
            User user=userMapper.selectUsername(username);
            int uid=user.getUid();
            return userImgService.getPic(uid);
        }else{
            return new UserImg();
        }

    }

}
