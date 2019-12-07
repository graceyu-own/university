package cn.falleaves.common

enum class Regex(var pattern: String) {

    NICKNAME("^[0-9a-zA-Z]{5, 30}$"),
    EMAIL("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-z]{2,}$"),
    PHONE("^1[3456789]\\d{9}$")

}