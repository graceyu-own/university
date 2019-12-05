package cn.falleaves.auth.utils;

public class ResponseData {

    private int httpCodePrefix = 0;
    private int httpCodeSuffix = 0;
    private String message = "";

    public int getHttpCodePrefix() {
        return httpCodePrefix;
    }

    public ResponseData setHttpCodePrefix(int httpCodePrefix) {
        this.httpCodePrefix = httpCodePrefix;
        return this;
    }

    public int getHttpCodeSuffix() {
        return httpCodeSuffix;
    }

    public ResponseData setHttpCodeSuffix(int httpCodeSuffix) {
        this.httpCodeSuffix = httpCodeSuffix;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseData setMessage(String message) {
        this.message = message;
        return this;
    }

    public void setAll(int httpCodePrefix, int httpCodeSuffix, String message) {
        this.httpCodePrefix = httpCodePrefix;
        this.httpCodeSuffix = httpCodeSuffix;
        this.message = message;
    }

    public static ResponseData notFound() {
        return new ResponseData().setHttpCodePrefix(404).setMessage("Not found");
    }

}
