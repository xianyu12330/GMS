package com.example.gms.controller;

import com.example.gms.dao.LoginMapper;

import com.example.gms.dao.UserMapper;
import com.example.gms.entity.User;
import com.example.gms.result.Code;
import com.example.gms.result.Result;
import com.example.gms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    UserController userController;
    @Autowired
    UserMapper userMapper;
//    登录
    @PostMapping("/log")
    public Result login(@RequestBody User user){
        String name = user.getName();
        String password = user.getPassword();
        boolean flag;
        System.out.println(name +"::"+password);
        User login = loginMapper.login(name, password);
        if(login != null){
            flag = true;
        } else {
            flag = false;
        }

        Result result = null;
        if (flag != false){
            String s = String.valueOf(userMapper.getRoleByName(name));
//            生成token
            String token  = JWTUtil.generateToken(name, s);
            // 返回token
            result = new Result(Code.TOKEN_GENERATE_SUC, "登录成功");

            result.addData(token);
        }else {
            // 用户名或者密码错误
            result = new Result(Code.LOGIN_FALSE, "账号或密码错误，请重试！");
            System.out.println(result);
        }
        User user1 = userMapper.userByName(name);
        result.addData(user1);
        return result;
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user){

        int register = loginMapper.insertUser(user);
        Result result = new Result(register == 0 ? Code.REGISTER_FALSE : Code.REGISTER_SUC, register == 0 ? "注册失败，请重试！" :"注册成功，您的用户名为"+user.getName() );
        result.addData(register);
        return result;
    }
}
