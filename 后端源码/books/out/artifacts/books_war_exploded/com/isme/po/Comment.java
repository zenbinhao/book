package com.isme.po;

import java.util.Date;

public class Comment {
    private int comment_id;
    private int user_id;
    private String user_name;
    private String content;
    private Date create_time;
    private int is_delete;
    private int book_id;

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", content='" + content + '\'' +
                ", create_time=" + create_time +
                ", is_delete=" + is_delete +
                ", book_id=" + book_id +
                '}';
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
