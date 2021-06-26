package com.isme.base.em;

public enum  ErrorMessageEnum {
    SYS_ERROR(1001,"系统异常请联系管理员"),
    NETWORK(1002,"网络连接失败"),
    LOGIN_TIMEOUT_ERROR(1003,"登录超时，请重新登录再试")
    ;
    private int code;
    private String message;

    ErrorMessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
