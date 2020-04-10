package com.onlineExam.domain;

import java.util.List;

public class Page {

    private int code;
    private String msg;
    private long count;
    private List<Object> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

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
