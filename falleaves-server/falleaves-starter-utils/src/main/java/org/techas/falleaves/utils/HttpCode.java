package org.techas.falleaves.utils;

public enum HttpCode {

    SUCCESS(200, 0),
    INTERNET_SERVER_ERROR(500, 0),

    IDENTIFIER_EMPTY(401, 1),
    CREDENTIAL_EMPTY(401, 2),
    NICKNAME_EMPTY(401, 3),
    EMAIL_EMPTY(401, 4),
    PASSWORD_EMPTY(401, 5),
    PASSWORD2_EMPTY(401, 6),

    ACCOUNT_NOT_EXISTS(401, 10),
    ACCOUNT_LOCKED(401, 11),
    INCORRECT_CREDENTIALS(401, 12),
    TWOPASSWORD_NOTMATCH(401, 13),
    EMAILVALID_EMPTY(401, 14),

    ;

    public int codeType;
    public int codeAppend;

    HttpCode(int codeType, int codeAppend) {
        this.codeType = codeType;
        this.codeAppend = codeAppend;
    }
}