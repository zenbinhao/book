package com.isme.po;

import java.util.Date;

public class Examine {
    private int examine_id;
    private String  purchase_book;
    private int examine_state;
    private Date create_time;
    private Date update_time;
    private  int is_delete;
    private int version;
    private int create_user_id;
    private String create_user_name;
    private int update_user_id;
    private String update_user_name;

    @Override
    public String toString() {
        return "Examine{" +
                "examine_id=" + examine_id +
                ", purchase_book='" + purchase_book + '\'' +
                ", examine_state=" + examine_state +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", is_delete=" + is_delete +
                ", version=" + version +
                ", create_user_id=" + create_user_id +
                ", create_user_name='" + create_user_name + '\'' +
                ", update_user_id=" + update_user_id +
                ", update_user_name='" + update_user_name + '\'' +
                '}';
    }

    public int getExamine_id() {
        return examine_id;
    }

    public void setExamine_id(int examine_id) {
        this.examine_id = examine_id;
    }

    public String getPurchase_book() {
        return purchase_book;
    }

    public void setPurchase_book(String purchase_book) {
        this.purchase_book = purchase_book;
    }

    public int getExamine_state() {
        return examine_state;
    }

    public void setExamine_state(int examine_state) {
        this.examine_state = examine_state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(int create_user_id) {
        this.create_user_id = create_user_id;
    }

    public String getCreate_user_name() {
        return create_user_name;
    }

    public void setCreate_user_name(String create_user_name) {
        this.create_user_name = create_user_name;
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
}
