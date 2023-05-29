package com.example.gms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface LoginMapper extends BaseMapper<User> {
    User getLoginUser(@Param("user_id")long user_id);
    int insertUser(@Param("User")User user);
    User login(@Param("name")String name,@Param("password") String password);
}
