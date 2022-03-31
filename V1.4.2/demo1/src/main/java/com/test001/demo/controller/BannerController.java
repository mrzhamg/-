package com.test001.demo.controller;

import com.test001.demo.entity.Banners;
import com.test001.demo.service.BannersService;
import com.test001.demo.util.ImageByte;
import com.test001.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpUtils;
import java.io.InputStream;
import java.util.List;

@RestController
public class BannerController {
    private Result result;

    @Autowired
    private BannersService bannersService;

    @GetMapping("/findBannerAll")
    public List<Banners> findBannerAll(){
        return bannersService.findBannerAll();
    }

}
