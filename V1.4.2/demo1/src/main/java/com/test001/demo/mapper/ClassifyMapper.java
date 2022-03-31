package com.test001.demo.mapper;

import com.test001.demo.entity.Classify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassifyMapper {
    public List<Classify> findClassifyAll();
}
