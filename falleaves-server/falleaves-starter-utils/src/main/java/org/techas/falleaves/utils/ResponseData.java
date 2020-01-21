package org.techas.falleaves.utils;

public class ResponseData<T>{

    private int codeType;
    private int codeAppend;
    private T data;

    public ResponseData() {
    }

    public ResponseData(int codeType, int codeAppend, T data) {
        this.codeType = codeType;
        this.codeAppend = codeAppend;
        this.data = data;
    }

    public ResponseData(HttpCode httpCode, T data) {
        this(httpCode.codeType, httpCode.codeAppend, data);
    }

    public int getCodeType() {
        return codeType;
    }

    public int getCodeAppend() {
        return codeAppend;
    }

    public ResponseData<T> setCode(int codeType, int codeAppend) {
        this.codeType = codeType;
        this.codeAppend = codeAppend;
        return this;
    }


    public T getData() {
        return data;
    }

    public ResponseData<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ResponseData<T> New(HttpCode rv, T data) {
        return new ResponseData().setCode(rv.codeType, rv.codeAppend).setData(data);
    }
}