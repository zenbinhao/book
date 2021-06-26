package com.isme.base.vo;

import com.isme.base.em.ErrorMessageEnum;

public class BusinessException extends RuntimeException{
    private int resultCode;
    private String message;
    // 自定义错误信息
    public BusinessException(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }
    // 重载 构造函数调用 枚举错误消息
    public BusinessException(ErrorMessageEnum errorMessageEnum){
        this.message = errorMessageEnum.getMessage();
        this.resultCode = errorMessageEnum.getCode();
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

