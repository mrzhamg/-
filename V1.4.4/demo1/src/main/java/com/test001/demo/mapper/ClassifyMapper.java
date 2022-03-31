package com.test001.demo.mapper;

import com.test001.demo.entity.Classify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassifyMapper {
    public List<Classify> findClassifyAll();

    public Classify findClassifyAllName(String name);

    public boolean insertClassify(String name);

    public boolean delClassify(int cid);

    public boolean updataClassify(String name,int cid);
}
