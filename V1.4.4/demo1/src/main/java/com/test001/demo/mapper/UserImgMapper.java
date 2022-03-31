package com.test001.demo.mapper;

import com.test001.demo.entity.UserImg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserImgMapper {
    @Insert("INSERT INTO userimg (uid,name,bid) values(#{uid},#{name},#{bid});")
    int add(UserImg userImg);

    @Select("SELECT * FROM userimg WHERE uid=#{uid}")
    UserImg getpic(int uid);

    @Update("UPDATE userimg SET name=#{name},bid=#{bid} WHERE uid=#{uid}")
    int update(UserImg userImg);
}
