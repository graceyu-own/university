package org.techas.falleaves.utils;

public class ResponseData<T>{

    private double code;
    private T data;

    public ResponseData() {
    }

    public ResponseData(double code, T data) {
        this.code = code;
        this.data = data;
    }

    public double getCode() {
        return code;
    }

    public ResponseData<T> setCode(double code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseData<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ResponseData<T> New(ReturnValue rv, T data) {
        return new ResponseData().setCode(rv.code).setData(data);
    }
}