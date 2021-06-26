package com.isme.po;

import java.util.Date;

public class Stock {
    private int stock_id;
    private Date create_time;
    private int create_user_id;
    private int book_id;

    @Override
    public String toString() {
        return "Stock{" +
                "stock_id=" + stock_id +
                ", create_time=" + create_time +
                ", create_user_id=" + create_user_id +
                ", book_id=" + book_id +
                '}';
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(int create_user_id) {
        this.create_user_id = create_user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
