package com.example.gms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("borrowequip")
public class borrow {
    @TableId(type = IdType.AUTO)
    private int borrowId;
    @TableField("username")
    private String username;
    @TableField("eName")
    private String eName;
    @TableField("gameName")
    private String gameName;
    @TableField("eNum")
    private int eNum;

    @TableField("borrowBegin")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date borrowBegin;

    @TableField("borrowEnd")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date borrowEnd;

    @TableField("money")
    private double money;
    @TableField("agree")
    private int agree;
}
