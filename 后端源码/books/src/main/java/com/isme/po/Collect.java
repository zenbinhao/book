package com.isme.po;

public class Collect {
    private int collect_id;
    private int user_id;
    private int book_id;

    @Override
    public String toString() {
        return "Collect{" +
                "collect_id=" + collect_id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                '}';
    }

    public int getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(int collect_id) {
        this.collect_id = collect_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
