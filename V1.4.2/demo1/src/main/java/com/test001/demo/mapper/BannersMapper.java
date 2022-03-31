package com.test001.demo.mapper;

import com.test001.demo.entity.Banners;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannersMapper {
    public List<Banners> findBannerAll();
}
