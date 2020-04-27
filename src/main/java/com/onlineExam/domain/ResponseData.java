package com.onlineExam.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseData<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    private ResponseData(int code) {
        this.code = code;
    }

    private ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseData(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private ResponseData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseData<T> success() {
        return new ResponseData<>(0);
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<>(0, data);
    }

    public static <T> ResponseData<T> success(String msg) {
        return new ResponseData<>(0, msg);
    }

    public static <T> ResponseData<T> success(T data, String msg) {
        return new ResponseData<>(0, msg, data);
    }

    public static <T> ResponseData<T> fail() {
        return new ResponseData<>(1);
    }

    public static <T> ResponseData<T> fail(T data) {
        return new ResponseData<>(1, data);
    }

    public static <T> ResponseData<T> fail(String msg) {
        return new ResponseData<>(1, msg);
    }

    public static <T> ResponseData<T> fail(T data, String msg) {
        return new ResponseData<>(1, msg, data);
    }

}
