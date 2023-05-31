package com.example.controller;

import com.example.entity.borrow;
import com.example.mapper.borrowMapper;
import com.example.result.Code;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class borrowController {
    @Autowired
    borrowMapper borrowMapper;
    @GetMapping("/allBorrow")
    public Result getBorrow(){
        List<borrow> allBorrow = borrowMapper.selectList(null);
        Result result = new Result(Code.SEARCH_SUC,"查询列表成功！");
        result.addData(allBorrow);
        return result;
    }

    @GetMapping("/BorrowByID/{user_id}")
    public Result BorrowByID(@PathVariable int user_id){
        borrow borrow = borrowMapper.selectById(user_id);
        Result result = new Result(Code.SEARCH_SUC,"查询成功！");
        result.addData(borrow);
        return result;
    }

    @PostMapping("insertBorrow")
    public Result insertBorrow(@RequestBody borrow borrow){
        int insert = borrowMapper.insert(borrow);
        Result result = new Result(insert==1 ? Code.NEWDATA_SUC : Code.NEWDATA_ERR, insert==1 ? "添加成功！" : "添加失败！");
        return result;
    }

    @PutMapping("/updateBorrow")
    public Result updateBorrow(@RequestBody borrow borrow){
        int update = borrowMapper.updateById(borrow);
        Result result = new Result(update==1 ? Code.UPDATA_SUC : Code.UPDATA_ERR, update==1 ? "修改成功！" : "修改失败，请重试！");
        return result;
    }

    @DeleteMapping("/deleteBorrow/{borrow_id}")
    public Result DeleteBorrow(@PathVariable int borrow_id){
        int delete = borrowMapper.deleteById(borrow_id);
        String msg = delete==1?"删除成功！":"不存在该用户，请重新操作！";
        return new Result(delete ==1?Code.DELETE_SUC :Code.DELETE_ERR,msg);
    }



}
