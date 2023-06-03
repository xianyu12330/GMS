package com.example.gms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gms.entity.borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper extends BaseMapper<borrow> {
   List<borrow>  borrowByUser(@Param("username")String username);
}
