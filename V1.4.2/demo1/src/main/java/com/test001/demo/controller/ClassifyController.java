package com.test001.demo.controller;

import com.test001.demo.entity.Classify;
import com.test001.demo.service.ClassifyService;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
