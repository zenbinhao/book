package com.isme.po;

import java.util.Date;

public class Record {
    private int record_id;
    private Date borrow_time;
    private Date return_time;
    private int user_id;
    private String user_name;
    private  int is_delete;
    private  int update_user_id;
    private String update_user_name;
    private  int book_state;
    private int book_id;
    private String book_title;

    @Override
    public String toString() {
        return "Record{" +
                "record_id=" + record_id +
                ", borrow_time=" + borrow_time +
                ", return_time=" + return_time +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", is_delete=" + is_delete +
                ", update_user_id=" + update_user_id +
                ", update_user_name='" + update_user_name + '\'' +
                ", book_state=" + book_state +
                ", book_id=" + book_id +
                ", book_title='" + book_title + '\'' +
                '}';
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public Date getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(Date borrow_time) {
        this.borrow_time = borrow_time;
    }

    public Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(Date return_time) {
        this.return_time = return_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(int update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public int getBook_state() {
        return book_state;
    }

    public void setBook_state(int book_state) {
        this.book_state = book_state;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }
}
