package com.isme.base.vo;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class BaseModel {
    private int code;
    private String message;
    private Object data;
    //上传文件的属性
    private MultipartFile[] multipartFiles;
    //上传文件的地址
    private String filesArray;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MultipartFile[] getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(MultipartFile[] multipartFiles) {
        this.multipartFiles = multipartFiles;
    }

    public String getFilesArray() {
        return filesArray;
    }

    public void setFilesArray(String filesArray) {
        this.filesArray = filesArray;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", multipartFiles=" + Arrays.toString(multipartFiles) +
                ", filesArray='" + filesArray + '\'' +
                '}';
    }
}
