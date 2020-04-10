package com.onlineExam.domain;

import lombok.Data;

import java.util.List;
@Data
public class Page {

    private int code;
    private String msg;
    private long count;
    private List<Object> data;


    public Page() {
    }

    public static Page success(long total, List<Object> data){
        Page layerMsg = new Page();
        layerMsg.setCode(0);
        layerMsg.setMsg("获取数据成功！");
        layerMsg.setCount(total);
        layerMsg.setData(data);
        return layerMsg;
    }


    public static Page fail(){
        Page layerMsg = new Page();
        layerMsg.setCode(0);
        layerMsg.setMsg("获取数据成功！");
        layerMsg.setCount(0);
        layerMsg.setData(null);
        return layerMsg;
    }
}
