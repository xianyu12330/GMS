package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("borrow")
public class borrow {
    @TableId(type = IdType.AUTO)
    private int borrowId;
    @TableField("userId")
    private int userId;
    @TableField("eId")
    private int eId;
    @TableField("AddressId")
    private int AddressId;
    @TableField("gameId")
    private int gameId;
    @TableField("eNum")
    private int eNum;
}
