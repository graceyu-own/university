package cn.falleaves.common;

public enum Regex {

    NICKNAME("^[0-9a-zA-Z]{5, 30}$"),
    EMAIL("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-z]{2,}$"),
    PHONE("^1[3456789]\\d{9}$")
    ;

    private String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
