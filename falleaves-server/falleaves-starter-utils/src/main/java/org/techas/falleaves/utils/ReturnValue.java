package org.techas.falleaves.utils;

public enum ReturnValue{

    SUCCESS(200.0, "Success"),
    INTERNET_SERVER_ERROR(500.0, "Internet server error"),

    IDENTIFIER_EMPTY(401.1, "Identifier is empty"),
    CREDENTIAL_EMPTY(401.2, "Credential is empty"),
    NICKNAME_EMPTY(401.3, "nickname is empty"),
    EMAIL_EMPTY(401.4, "email is empty"),
    PASSWORD_EMPTY(401.5, "password is empty"),
    PASSWORD2_EMPTY(401.6, "Two password is empty"),

    INCORRECT_CREDENTIALS(401.10, "Incorrect credentials"),
    TWOPASSWORD_NOTMATCH(401.11, "The two password do not match")

        ;

    private double code;
    private Object data;

    ReturnValue(double code, Object data) {
        this.code = code;
        this.data = data;
    }

    public double getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}