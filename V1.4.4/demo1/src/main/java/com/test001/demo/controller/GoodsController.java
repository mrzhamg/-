package com.test001.demo.controller;

import com.test001.demo.entity.Goods;
import com.test001.demo.entity.Shangjia_user;
import com.test001.demo.mapper.GoodsMapper;
import com.test001.demo.mapper.Shangjia_userMapper;
import com.test001.demo.service.GoodsService;
import com.test001.demo.util.Result;
import com.test001.demo.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
public class GoodsController {
    private Result result;
    public Token token;
    public int limit=0;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private Shangjia_userMapper shangjia_userMapper;

    @GetMapping("/findGoodsAll")
    public List<Goods> findGoodsAll() {
        limit=0;
        return goodsService.goodsList();
    }

    @GetMapping("/findGoodsLimit")
    public Result findGoodsLimit(@RequestParam int num){
        List<Goods> data = new ArrayList<>();
        data = goodsService.findGoodsLimit(num);
        if(limit==data.size()){
            return new Result(500,"所有商品已加载完毕！！！",null);
        }else {
            limit = data.size();
            Map<String, Object> data1 = new HashMap<>();
            data1.put("data", data);
            return new Result(200, "操作成功", data1);
        }
    }

    @GetMapping("/goodes")
    public Goods getsidfindsj(@RequestParam int sid) {
        return goodsService.getsidfindsj(sid);
    }

    //根据商家id获取商品
    @GetMapping("/getSjidfindGoods")
    public List<Goods> findGoodsBySid(@RequestParam String sjtoken) {
//        if(sjtoken.toString()!=null){
//            return new Result(500,"请登录商家账户",null);
//        }
        String sjuser = token.getSj_user(sjtoken);
        Shangjia_user shangjia_user = shangjia_userMapper.getSjuser(sjuser);
        int sid = shangjia_user.getSjid();
        return goodsService.sjGoodsList(sid);
    }

    //搜索框查询
    @GetMapping("getSearch")
    public List<Goods> getSearch(@RequestParam("searchs") String search) {
        String ss = "%" + search + "%";
        System.out.println("搜索内容=>" + ss);
        return goodsService.getSearch(ss);
    }


    //上传商品
    @PostMapping("/uploadShop")
    public Result uploadShop(@RequestParam MultipartFile file, @RequestParam("sjtoken") String sjtoken, @RequestParam("shopName") String spms, @RequestParam("shopPrice") float price,@RequestParam("isClassify") String isClassify, @RequestParam("miaoshu") String miaoshu) throws IOException {
        //判断D盘是否存在路径 D:\MiYouStore_data\shoplist
        File fileMY_data = new File("C:\\MiYouStore_data");
        File isfile = new File("C:\\MiYouStore_data\\shoplist");
        //如果文件夹不存在则创建
        if (!fileMY_data.exists() && !fileMY_data.isDirectory() && !isfile.exists() && !isfile.isDirectory()) {
            fileMY_data.mkdir();
            isfile.mkdir();
            return new Result(500, "经检测该路径不存在，已自动创建MiYouStore_data/shoplist文件夹，请重新上传", null);
        }  else {
            System.out.println("//目录存在");
            System.out.println("goods=>"+sjtoken+";描述=》"+spms+";价格=>"+price+";分类=>"+isClassify);

            String name =file.getOriginalFilename();
            String type = name.substring(name.lastIndexOf("."));
            UUID uuid = UUID.randomUUID();
//        File directory = new File("src/main/resources/static/");//拼接路径
//        String paths = directory.getCanonicalPath();
//        System.out.println("name=>"+name+";path=>"+paths);
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\MiYouStore_data\\shoplist\\" + uuid.toString()+ type);
            fileOutputStream.write(file.getBytes());//写入硬盘
//        System.out.println("图片字节流=》"+file.getBytes());
            fileOutputStream.close();//关闭字节流

            String sjuser = token.getSj_user(sjtoken);
            Shangjia_user shangjia_user = shangjia_userMapper.getSjuser(sjuser);
            int sid = shangjia_user.getSjid();
            //new一个商品
            Goods goodsitem=new Goods();
            goodsitem.setSjid(sid);
            goodsitem.setDescribe(spms);
            goodsitem.setPrice(price);
            goodsitem.setClassify(isClassify);
            goodsitem.setMiaoshu(miaoshu);
            goodsitem.setIsputaway(true);
            goodsitem.setImg("MiYouStore_data\\shoplist\\"+uuid.toString()+type);
            goodsService.insertGoods(goodsitem);
            return new Result(200,"商品已上传" ,null);
        }

    }

//    商品上下架
    @PostMapping("/UpdataGoods")
    public Result UpdataGoods(@RequestBody Goods goods, @RequestHeader("sjtoken") String sjtoken){
//        System.out.println(goods);
//        System.out.println(token);
        int sid=goods.getSid();
        boolean isputaway=goods.getIsputaway();
        // 判断商家是否登录
        if(token.sjverify(sjtoken)){
            goodsService.upGoodsIsputaway(sid,isputaway);
            if(isputaway){
                return new Result(200,"该商品已下架",null);
            }else{
                return new Result(200,"该商品已上架",null);
            }
        }else{
            goodsService.upGoodsIsputaway(sid,isputaway);
            return new Result(500,"身份验证失败！请重新登录",null);
        }
    }

    @GetMapping("/sjgoodes")
    public Goods getsjGood(@RequestParam int sid){
        return goodsService.getsidfindsj(sid);
    }

    //修改商品信息
    @PostMapping("/UpGood")
    public Result UpdataGoodsAll(@RequestBody Goods goods, @RequestHeader("sjtoken") String sjtoken){
        System.out.println(goods);
        int sid = goods.getSid();
        String describe = goods.getDescribe();
        float price = goods.getPrice();
        String classify = goods.getClassify();
        String miaoshu = goods.getMiaoshu();
        // 判断商家是否登录
        if(token.sjverify(sjtoken)){
            goodsService.UpdataGoodsAll(sid,describe,price,classify,miaoshu);
            return new Result(200,"商品信息修改成功",null);
        }else{
            return new Result(500,"身份验证失败！请重新登录",null);
        }
    }

    //删除商品
    @GetMapping("/delGood")
    public Result delGood(@RequestParam int sid){
        System.out.println("sid=>"+sid);
        goodsService.delGood(sid);
        return new Result(200,"商品删除成功",null);
    }
}
