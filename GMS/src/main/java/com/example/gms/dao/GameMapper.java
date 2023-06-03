package com.example.gms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gms.entity.game;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GameMapper extends BaseMapper<game>{
   game GameByName(@Param("gameName")String gameName);
}
