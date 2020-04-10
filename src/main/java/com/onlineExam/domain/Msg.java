package com.onlineExam.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Msg {
    private int code;
    private String msg;
    private Map<String, Object> extend = new HashMap<String, Object>();


    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功！");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败！");
        return result;
    }

    public Msg add(String key, Object value){
        this.getExtend().put(key, value);
        return this;
    }
}

