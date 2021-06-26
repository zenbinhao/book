package com.isme.service;

import com.isme.base.vo.BaseModel;
import com.isme.po.Comment;

public interface CommentService {
    BaseModel selectCommentByBookId(int book_id,int curr_page,int page_size)throws Exception;
    BaseModel selectPageComment(String userName,int curr_page,int page_size) throws Exception;
    BaseModel deleteComment(String ids)throws Exception;
    BaseModel insertComment(Comment comment)throws Exception;
}
