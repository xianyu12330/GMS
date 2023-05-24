package com.example.gms.result;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private List<Object> data = new ArrayList<>();
    private Integer code;
    private String msg;
    public Result() {
    }

    /* 设定两种重构，只给code，给code与msg*/
    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public List<Object> getData() {
        return data;
    }

    public void addData(Object value) {
        data.add(value);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
