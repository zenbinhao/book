package com.isme.po;

import java.util.Date;

public class LoginLog {
    private int loginLog_id;
    private int user_id;
    private String user_account ;
    private String user_name;
    private Date login_date;
    private String ip;

    @Override
    public String toString() {
        return "LoginLog{" +
                "loginLog_id=" + loginLog_id +
                ", user_id=" + user_id +
                ", user_account='" + user_account + '\'' +
                ", user_name='" + user_name + '\'' +
                ", login_date=" + login_date +
                ", ip='" + ip + '\'' +
                '}';
    }

    public int getLoginLog_id() {
        return loginLog_id;
    }

    public void setLoginLog_id(int loginLog_id) {
        this.loginLog_id = loginLog_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getLogin_date() {
        return login_date;
    }

    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
