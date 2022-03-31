package com.test001.demo.service;

import com.test001.demo.entity.Goods;
import com.test001.demo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> goodsList(){
        return goodsMapper.findGoodsAll();
    };

    //根据商家id获取商品
    public List<Goods> sjGoodsList(int sid) {return goodsMapper.getSidfindGoodsList(sid);};

    public Goods getsidfindsj(int sid){return goodsMapper.getsidfindsj(sid);}

    //搜索查询
    public List<Goods> getSearch(String search){
        return goodsMapper.getSearch(search);
    }

    public void insertGoods(Goods goods){
        System.out.println(goods.getSjid()+goods.getDescribe()+goods.getPrice()+goods.getImg());
        goodsMapper.insertGoods(goods.getSjid(),goods.getDescribe(),goods.getPrice(),goods.getImg());
    }

    //修改商品是否上架
    public void upGoodsIsputaway(int sid,boolean isputaway){
        goodsMapper.upGoodsIsputaway(sid,isputaway);
    }

    //修改商品信息
    public void UpdataGoodsAll(int sid,String describe, float price, String classify, String miaoshu){
        System.out.println(sid+describe+price+classify+miaoshu);
        goodsMapper.UpdataGoodsAll(sid,describe,price,classify,miaoshu);
        System.out.println(sid+describe+price+classify+miaoshu);
    };
    //删除商品
    public void  delGood(int sid){
        goodsMapper.delGood(sid);
    }
}
