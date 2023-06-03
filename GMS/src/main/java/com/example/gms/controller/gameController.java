package com.example.gms.controller;


import com.example.gms.dao.GameMapper;
import com.example.gms.entity.equipment;
import com.example.gms.entity.game;
import com.example.gms.result.Code;
import com.example.gms.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class gameController {
    @Autowired
    GameMapper gameMapper;
    @GetMapping("/allGame")
    public Result getGame() {
        List<game> allGame = gameMapper.selectList(null);
        Result result = new Result(Code.SEARCH_SUC, "查询列表成功!");
        result.addData(allGame);
        return result;
    }

    @GetMapping("/GameByID/{gameid}")
    public Result GameByID(@PathVariable int gameid){
        game game = gameMapper.selectById(gameid);
        Result result = new Result(Code.SEARCH_SUC,"查询成功！");
        result.addData(game);
        return result;
    }

    @PostMapping("/insertGame")
    public Result insertGame(@RequestBody game game){
        int insert = gameMapper.insert(game);
        Result result = new Result(insert==1 ? Code.NEWDATA_SUC : Code.NEWDATA_ERR, insert==1 ? "添加成功！" : "添加失败！");
        return result;
    }
    @PutMapping("/updateGame")
    public Result updateGame(@RequestBody game game){
        int update = gameMapper.updateById(game);
        Result result = new Result(update==1 ? Code.UPDATA_SUC : Code.UPDATA_ERR, update==1 ? "修改成功！" : "修改失败，请重试！");
        return result;
    }
    @DeleteMapping("/deleteGame/{gameid}")
    public Result DeleteGame(@PathVariable int gameid){
        int delete = gameMapper.deleteById(gameid);
        String msg = delete==1?"删除成功！":"不存在该用户，请重新操作！";
        return new Result(delete ==1?Code.DELETE_SUC :Code.DELETE_ERR,msg);
    }
    @GetMapping("/GameByName/{gameName}")
    public Result GameByName(@PathVariable String gameName){
        game game = gameMapper.GameByName(gameName);
        Result result = new Result(Code.SEARCH_SUC,"查询成功！");
        result.addData(game);
        return result;
    }
}
