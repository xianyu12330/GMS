package com.example.gms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("address")
public class place {

    @TableId(type = IdType.AUTO)
    private int AddressId;

    @TableField("AddressName")
    private String addressName;

    @TableField("state")
    private String state;

    @TableField("money")
    private String money;

}
