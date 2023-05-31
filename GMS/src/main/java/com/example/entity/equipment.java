package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
@TableName("equipment")
public class equipment {
    @TableId(type = IdType.AUTO)
    private int Eid;
    @TableField("Ename")
    private String Ename;
    @TableField("Buyday")
    private Date Buyday;
    @TableField("count")
    private int count;
    @TableField("cost")
    private double cost;
    @TableField("state")
    private int state;
}
