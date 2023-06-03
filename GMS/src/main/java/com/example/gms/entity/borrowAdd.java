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

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("borrowAdd")
public class borrowAdd {
    @TableId(type = IdType.AUTO)
    private int borrowAdd_id;
    @TableField("username")
    private String username;

    @TableField("AddressName")
    private String AddressName;


    @TableField("borrowBegin")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize
    private Date borrowBegin;

    @TableField("borrowEnd")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize
    private Date borrowEnd;

    @TableField("money")
    private double money;
    @TableField("agree")
    private int agree;
}
