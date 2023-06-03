package com.example.gms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gms.entity.equipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EquipmentMapper extends BaseMapper<equipment> {
    equipment equipByName(@Param("EName")String EName);

    int getCount(@Param("ename") String ename);
}
