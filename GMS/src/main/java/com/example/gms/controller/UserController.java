package com.example.gms.controller;

import com.example.gms.dao.UserMapper;
import com.example.gms.entity.User;
import com.example.gms.result.Code;
import com.example.gms.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;
    @GetMapping("/allUser")
    public Result getUsers(){
        List<User> allUser = userMapper.selectList(null);
        Result result = new Result(Code.SEARCH_SUC,"查询列表成功！");
        result.addData(allUser);
        return result;
    }

    @GetMapping("/UserById/{user_id}")
    public Result UserByID(@PathVariable int user_id){
        User user = userMapper.selectById(user_id);
        Result result = new Result(Code.SEARCH_SUC,"查询成功！");
        result.addData(user);
        return result;
    }

    @PostMapping("/insertUser")
    public Result insertUser(@RequestBody User user){
        int insert = userMapper.insert(user);
        Result result = new Result(insert==1 ? Code.NEWDATA_SUC : Code.NEWDATA_ERR, insert==1 ? "添加成功！" : "添加失败！");
        return result;
    }

    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        int update = userMapper.updateById(user);
        Result result = new Result(update==1 ? Code.UPDATA_SUC : Code.UPDATA_ERR, update==1 ? "修改成功！" : "修改失败，请重试！");
        return result;
    }

    @DeleteMapping("/deleteUser/{user_id}")
    public Result DeleteUser(@PathVariable int user_id){
        int delete = userMapper.deleteById(user_id);
        String msg = delete==1?"删除成功！":"不存在该用户，请重新操作！";
        return new Result(delete ==1?Code.DELETE_SUC :Code.DELETE_ERR,msg);
    }
    @GetMapping("/userByName/{name}")
    public Result GetuserByname(@PathVariable String name){
        User user = userMapper.userByName(name);
        Result result = new Result(Code.SEARCH_SUC,"查询成功！");
        result.addData(user);
        return result;
    }
    @PutMapping("/updateRole/{id}/{role}")
    public Result updateRole(@PathVariable long id,@PathVariable int role){
        boolean flag= userMapper.updateRole(id, role);
        Result result = new Result(flag ? Code.UPDATA_SUC : Code.UPDATA_ERR, flag ? "修改成功！" : "修改失败，请重试！");
        return  result;
    }


}
