package com.test001.demo.service;

import com.test001.demo.entity.Classify;
import com.test001.demo.mapper.ClassifyMapper;
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
}
