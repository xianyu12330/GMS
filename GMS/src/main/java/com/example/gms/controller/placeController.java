package com.example.gms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.gms.dao.PlaceMapper;
import com.example.gms.entity.place;
import com.example.gms.result.Code;
import com.example.gms.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class placeController {
    @Autowired
    PlaceMapper placeMapper;
    @GetMapping("/allplaces")
    public Result getPlaces() {
        List<place> allplaces = placeMapper.selectList(null);
        Result result = new Result(Code.SEARCH_SUC, "查询列表成功!");
        result.addData(allplaces);
        return result;
    }

    @GetMapping("/PlaceByID/{place_id}")
    public Result PlaceByID(@PathVariable int place_id){
        place place = placeMapper.selectById(place_id);
        Result result = new Result(Code.SEARCH_SUC,"查询成功！");
        result.addData(place);
        return result;
    }


    @PostMapping("/insertPlace")
    public Result insertPlace(@RequestBody place place){
        int insert = placeMapper.insert(place);
        Result result = new Result(insert==1 ? Code.NEWDATA_SUC : Code.NEWDATA_ERR, insert==1 ? "添加成功！" : "添加失败！");
        return result;
    }

    @PutMapping("/updatePlace")
    public Result updatePlace(@RequestBody place place){
        int update = placeMapper.updateById(place);
        Result result = new Result(update==1 ? Code.UPDATA_SUC : Code.UPDATA_ERR, update==1 ? "修改成功！" : "修改失败，请重试！");
        return result;
    }

    @DeleteMapping("/deletePlace/{place_id}")
    public Result DeleteBorrow(@PathVariable int place_id){
        int delete = placeMapper.deleteById(place_id);
        String msg = delete==1?"删除成功！":"不存在该场地，请重新操作！";
        return new Result(delete ==1?Code.DELETE_SUC :Code.DELETE_ERR,msg);
    }
    @GetMapping("/freePlace")
    public Result GetFreePlace(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("state",0);
        List list = placeMapper.selectList(wrapper);
        Result result = new Result(Code.SEARCH_SUC, "查询列表成功!");
        result.addData(list);
        return result;
    }
    @PutMapping("/changeState/{AddressName}/{state}")
    public Result changeState(@PathVariable String AddressName ,@PathVariable int state){
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("AddressName",AddressName);
        updateWrapper.set("state",state);
        int update = placeMapper.update(null, updateWrapper);
        Result result = new Result(update==1 ? Code.UPDATA_SUC : Code.UPDATA_ERR, update==1 ? "修改成功！" : "修改失败，请重试！");
        return result;
    }
}
