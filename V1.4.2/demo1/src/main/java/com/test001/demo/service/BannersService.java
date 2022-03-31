package com.test001.demo.service;

import com.test001.demo.entity.Banners;
import com.test001.demo.mapper.BannersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannersService {
    @Autowired
    private BannersMapper bannersMapper;

    public List<Banners> findBannerAll(){
        return bannersMapper.findBannerAll();
    };
}
