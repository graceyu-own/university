package org.techas.falleaves.utils;

public enum Regex {

    NICKNAME("^[0-9a-zA-Z]{5,30}$"),
    EMAIL("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"),
    PHONE("^1[3456789]\\d{9}$"),
    PASSWORD("^[0-9a-zA-Z]{6,30}$")

    ;

    private String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}