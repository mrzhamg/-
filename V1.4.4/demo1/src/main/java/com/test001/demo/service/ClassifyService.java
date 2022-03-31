package com.test001.demo.service;

import com.test001.demo.entity.Classify;
import com.test001.demo.mapper.ClassifyMapper;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyService {
    @Autowired
    private ClassifyMapper classifyMapper;

    public List<Classify> findClassifyAll(){
        return classifyMapper.findClassifyAll();
    }

    public Classify findClassifyAllName(String name){return classifyMapper.findClassifyAllName(name);}

    public Boolean insertClassify(String name){return classifyMapper.insertClassify(name);}

    public Boolean updataClassify(String name, int cid){return classifyMapper.updataClassify(name,cid);}

    public Boolean delClassify(int cid){return classifyMapper.delClassify(cid);}
}
