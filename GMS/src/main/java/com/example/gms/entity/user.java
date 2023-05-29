package com.example.gms.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer user_id;
    @TableField("name")
    private String name;
    @TableField("password")
    private String password;
    @TableField("birthday")
    private Date birthday;
    @TableField("email")
    private String email;
    @TableField("phone")
    private String phone;
    @TableField("role")
    private int role;
    @TableField("sex")
    private String sex;
}
