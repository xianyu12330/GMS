package com.example.controller;

import com.example.entity.equipment;
import com.example.mapper.equipmentMapper;
import com.example.result.Code;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/equip")
public class equipmentController {
    @Autowired
    equipmentMapper equipmentMapper;
    @GetMapping("/allEquip")
    public Result getEquipment(){
        List<equipment> allEquip = equipmentMapper.selectList(null);
        Result result = new Result(Code.SEARCH_SUC,"查询列表成功!");
        result.addData(allEquip);
        return result;
    }

    @GetMapping("/EquipByID/{equip_id}")
    public Result EquipByID(@PathVariable int equip_id){
        equipment equipment = equipmentMapper.selectById(equip_id);
        Result result = new Result(Code.SEARCH_SUC,"查询成功！");
        result.addData(equipment);
        return result;
    }

    @PostMapping("/insertEquip")
    public Result insertEquip(@RequestBody equipment equipment){
        int insert = equipmentMapper.insert(equipment);
        Result result = new Result(insert==1 ? Code.NEWDATA_SUC : Code.NEWDATA_ERR, insert==1 ? "添加成功！" : "添加失败！");
        return result;
    }
    @PutMapping("/updateEquip")
    public Result updateEquip(@RequestBody equipment equipment){
        int update = equipmentMapper.updateById(equipment);
        Result result = new Result(update==1 ? Code.UPDATA_SUC : Code.UPDATA_ERR, update==1 ? "修改成功！" : "修改失败，请重试！");
        return result;
    }
    @DeleteMapping("/deleteEquip/{equip_id}")
    public Result DeleteEquip(@PathVariable int equip_id){
        int delete = equipmentMapper.deleteById(equip_id);
        String msg = delete==1?"删除成功！":"不存在该用户，请重新操作！";
        return new Result(delete ==1?Code.DELETE_SUC :Code.DELETE_ERR,msg);
    }
}
