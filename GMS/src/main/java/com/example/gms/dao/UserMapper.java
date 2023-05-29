package com.example.gms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//    int getRoleByName(@Param("name")String name);

    User userByName(@Param("name")String name);

    int getRoleByName(@Param("name")String name);

    boolean updateRole(@Param("user_id") long user_id,@Param("role") int role);
}
