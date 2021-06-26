package com.isme.po;

import java.util.Date;

public class Books {
    private int book_id;
    private String book_title;
    private String introduce;
    private String author;
    private String book_picture;
    private String stock;//点赞数
    private Date create_time;
    private Date update_time;
    private int is_delete;
    private int version;
    private int create_user_id;
    private int count;
    private String create_user_name;
    private int update_user_id;
    private String update_user_name;
    private int category_id;

    @Override
    public String toString() {
        return "Books{" +
                "book_id=" + book_id +
                ", book_title='" + book_title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", author='" + author + '\'' +
                ", book_picture='" + book_picture + '\'' +
                ", stock='" + stock + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", is_delete=" + is_delete +
                ", version=" + version +
                ", create_user_id=" + create_user_id +
                ", count=" + count +
                ", create_user_name='" + create_user_name + '\'' +
                ", update_user_id=" + update_user_id +
                ", update_user_name='" + update_user_name + '\'' +
                ", category_id=" + category_id +
                '}';
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_picture() {
        return book_picture;
    }

    public void setBook_picture(String book_picture) {
        this.book_picture = book_picture;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
