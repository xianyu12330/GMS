package com.example.gms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@TableName("equipment")
public class equipment {
    @TableId(type = IdType.AUTO)
    private int Eid;
    @TableField("EName")
    private String ename;
    @TableField("Buyday")
    private String Buyday;
    @TableField("count")
    private int count;
    @TableField("money")
    private double money;
    @TableField("state")
    private int state;
}
