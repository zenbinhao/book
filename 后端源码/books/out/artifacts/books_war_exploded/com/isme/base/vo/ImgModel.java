package com.isme.base.vo;

import java.util.List;

public class ImgModel {
    private int errno;
    private List data;


    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ImgModel{" +
                "errno=" + errno +
                ", data=" + data +
                '}';
    }
}
