package com.isme.dao;

import com.isme.po.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {
    // 用户的看的
    List<Comment> selectCommentByBookId(@Param("id") int book_id);

    // 管理员看的
    List<Comment> selectPageComment(@Param("name") String userName);

    // 用户评论
    int insertComment(Comment comment);

    // 管理员删除
    int deleteComment(@Param("ids") String[] ids);
}
