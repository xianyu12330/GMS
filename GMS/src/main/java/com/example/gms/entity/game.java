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
@TableName("game")
public class game {
    @TableId(type = IdType.AUTO)
    private int gameId;
    @TableField("gameName")
    private String gameName;
    @TableField("gameBeginTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gameBeginTime;
    @TableField("gameEndTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gameEndTime;
    @TableField("gameAdd")
    private String gameAdd;
    @TableField("gameEvent")
    private String gameEvent;
    @TableField("gameEquip")
    private String gameEquip;
}
