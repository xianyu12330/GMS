package com.example.gms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gms.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
