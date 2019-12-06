package cn.falleaves.common;

/**
 * 响应数据类(用作与向前端返回数据)
 *
 * @author graceyu
 * @since 1.8
 */
public class ResponseData<T> {

    private double code = 0;
    private T data = null;

    public ResponseData() {
    }

    public ResponseData(double code, T data) {
        this.code = code;
        this.data = data;
    }

    public double getCode() {
        return code;
    }

    public ResponseData setCode(double code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseData setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ResponseData<T> New(ReturnValue rv) {
        if(null == rv.getData()) return new ResponseData<>().setCode(rv.getCode());
        return new ResponseData<T>(rv.getCode(), (T)rv.getData());
    }

    public static <T> ResponseData<T> New(double code, T data) {
        return new ResponseData<>(code, data);
    }
}
