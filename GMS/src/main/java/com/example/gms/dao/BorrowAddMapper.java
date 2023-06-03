package com.example.gms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.gms.entity.borrowAdd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowAddMapper extends BaseMapper<borrowAdd> {
    List<borrowAdd> borrowAddByUser(@Param("username") String username);
}
