package com.test001.demo.controller;

import com.test001.demo.entity.Classify;
import com.test001.demo.service.ClassifyService;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassifyController {
    private Result result;
    @Autowired
    private ClassifyService classifyService;

    @GetMapping("/getClassify")
    public List<Classify> findClassifyAll(){
        return classifyService.findClassifyAll();
    }

    @PostMapping("/insertClassify")
    public Result insertClassify(@RequestBody Classify classify){
        String name = classify.getName();
        if(isCunZai(name)){
            Boolean isInsert = classifyService.insertClassify(name);
            return new Result(200,"分类添加成功！",null);
        }else{
            return new Result(400,"该分类已存在！",null);
        }
    }

    public Boolean isCunZai(String name){
        Classify iscz = classifyService.findClassifyAllName(name);
        if(iscz==null){
            return true;
        }else {
            return false;
        }
    }

    @GetMapping("/delClassify")
    public Result delClassify(@RequestParam int cid){
        Boolean isDel = classifyService.delClassify(cid);
        if(isDel){
            return new Result(200,"该分类已删除！",null);
        }else {
            return new Result(400,"该分类删除失败！",null);
        }
    }

    @PostMapping("/updataClassify")
    public Result updataClass(@RequestBody Classify classify){
        String name = classify.getName();
        int cid = classify.getCid();
        if(isCunZai(name)){
            Boolean isUpDataClassify = classifyService.updataClassify(name,cid);
            return new Result(200,"该分类已修改！",null);
        }else{
            return new Result(400,"该分类名称已存在！",null);
        }
    }
}
