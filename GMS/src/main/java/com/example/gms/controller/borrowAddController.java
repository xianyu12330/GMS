package com.example.gms.controller;

import com.example.gms.dao.BorrowAddMapper;
import com.example.gms.entity.borrow;
import com.example.gms.entity.borrowAdd;
import com.example.gms.result.Code;
import com.example.gms.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowAdd")
public class borrowAddController {
    @Autowired
    BorrowAddMapper borrowAddMapper;
    @GetMapping("/allBorrow")
    public Result getBorrow(){
        List<borrowAdd> allBorrow = borrowAddMapper.selectList(null);
        Result result = new Result(Code.SEARCH_SUC,"查询列表成功！");
        result.addData(allBorrow);
        return result;
    }
    @GetMapping("/BorrowById/{borrowAdd_id}")
    public Result BorrowAddByID(@PathVariable int borrowAdd_id){
        borrowAdd borrow = borrowAddMapper.selectById(borrowAdd_id);
        Result result = new Result(Code.SEARCH_SUC,"查询成功！");
        result.addData(borrow);
        return result;
    }
    @PostMapping("insertBorrow")
    public Result insertBorrow(@RequestBody borrowAdd borrow){
        int insert = borrowAddMapper.insert(borrow);
        Result result = new Result(insert==1 ? Code.NEWDATA_SUC : Code.NEWDATA_ERR, insert==1 ? "添加成功！" : "添加失败！");
        return result;
    }
    @PutMapping("/updateBorrow")
    public Result updateBorrow(@RequestBody borrowAdd borrow){
        int update = borrowAddMapper.updateById(borrow);
        Result result = new Result(update==1 ? Code.UPDATA_SUC : Code.UPDATA_ERR, update==1 ? "修改成功！" : "修改失败，请重试！");
        return result;
    }
    @DeleteMapping("/deleteBorrow/{borrowAdd_id}")
    public Result DeleteBorrow(@PathVariable int borrowAdd_id){
        int delete = borrowAddMapper.deleteById(borrowAdd_id);
        String msg = delete==1?"删除成功！":"不存在该用户，请重新操作！";
        return new Result(delete ==1?Code.DELETE_SUC :Code.DELETE_ERR,msg);
    }
    @GetMapping("/borrowAddByUser/{username}")
    public Result borrowAddByUser(@PathVariable String username){
        List<borrowAdd> borrows = borrowAddMapper.borrowAddByUser(username);
        Result result = new Result(Code.SEARCH_SUC,"查询成功！");
        result.addData(borrows);
        return result;
    }
}
