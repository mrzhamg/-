package com.test001.demo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Goods {
    private int sid;//商品id
    private int sjid;//商家id
    private String describe;//商品描述
    private float price;//商品单价
    private String img;//商品图片路径
    private String classify;//商品分类
    private Boolean isputaway;
    private String miaoshu;

    //关联商家类
    private List<Shangjia_user> shangjia_user;

    public Goods(){
        shangjia_user=new ArrayList<>();
    }
}
