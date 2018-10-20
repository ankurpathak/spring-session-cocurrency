package com.github.ankurpathak.springsession;

public enum ApiCode {
    FOUND(-1),
    NOT_FOUND(-2),
    SUCCESS(0),
    USER_NOT_FOUND(1),
    USER_FOUND(2),
    FORBIDDEN(3),
    UNAUTHORIZED(4),
    REQUIRED_QUERY_PARAM(5),
    PAGE_NOT_FOUND(6),
    EMAIL_FOUND(7),
    CONTACT_FOUND(8),
    USERNAME_FOUND(9),
    UNKNOWN(17),
    VALIDATION(5),
    INCORRECT_STATE(19),
    ACCOUNT_DISABLED(10),
    BAD_REQUEST(11),
    INVALID_TOKEN(12),
    EXPIRED_TOKEN(13),
    INVALID_RSQL(14),
    INVALID_JSON(15),
    INVALID_PASSWORD(16),
    INVALID_PATCH(20);
    int code;

    ApiCode(int code){
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
