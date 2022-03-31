package com.test001.demo.mapper;

import com.test001.demo.entity.Shangjia_user;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Shangjia_userMapper {
    public Shangjia_user getSj_id();

    public Shangjia_user getSjuser(String sjuser);

    public Shangjia_user getSjname(String sjname);

    public Shangjia_user getSjphone(String sjphone);

    public Shangjia_user storelogin(String user,String password);

    public void insertSjUser(String user,String password,String name,String phone);
}
