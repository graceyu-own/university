package org.techas.falleaves.utils;

public interface Attr {

    interface MillisecondCalc {

        long MILLISECOND = 1;
        long SECOND = MILLISECOND * 1000;
        long MINUTE = SECOND * 60;
        long HOUR   = MINUTE * 60;
    }

    /* shiro缓存前缀 and 超时时间 : 30min */
    String REDIS_SHIRO_CACHE_PREFIX = "shiro-cache:";
    long REDIS_SHIRO_CACHE_EXPIRE = MillisecondCalc.MINUTE * 30;

    /* shiro会话前缀 and 超时时间 : 30min */
    String REDIS_SHIRO_SESSION_PREFIX = "shiro-session:";
    long REDIS_SHIRO_SESSION_EXPIRE = MillisecondCalc.MINUTE * 30;

    /* 用户注册邮件验证前缀 and 超时时间 : 10min */
    String REDIS_AUTH_REGISTER_MAIL_PREFIX = "auth-register-mail-valid:";
    long REDIS_AUTH_REGISTER_MAIL_EXPIRE = MillisecondCalc.MINUTE * 10;

    /* Session最后发送邮件时间前缀 and 超时时间 : 10min */
    String REDIS_LAST_SEND_MAIL_TIME_SESSION_PREFIX = "last-send-mail-time-session:";
    long REDIS_LAST_SEND_MAIL_TIME_SESSION_EXPIRE = MillisecondCalc.MINUTE * 10;

    /* Email最好发送邮件时间前缀 and 超时时间 : 10min */
    String REDIS_LAST_SEND_MAIL_TIME_EMAIL_PREFIX = "last-send-mail-time-email:";
    long REDIS_LAST_SEND_MAIL_TIME_EMAIL_EXPIRE = MillisecondCalc.MINUTE * 10;

    // 发送邮件倒数计时 2min
    long SEND_MAIL_COUNTDOWN = MillisecondCalc.MINUTE * 2;

}
