package com.zss.commons.web.domain;

public enum ResultStatus {
    SUCCESS(200),
    ILLEGAL_PARAMETER(400),
    UNAUTHORIZED(401),//未认证,
    FAIL(402),
    NOT_FUND(404),//
    INTERNAL_SERVER_ERROR(500),//服务异常
    ;
    private int status;

    ResultStatus(int status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
