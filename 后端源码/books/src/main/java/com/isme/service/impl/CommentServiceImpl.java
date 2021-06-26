package com.isme.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.CommentDao;
import com.isme.po.Comment;
import com.isme.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    public BaseModel selectCommentByBookId(int book_id,int curr_page,int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page, page_size);
        List<Comment> list =  commentDao.selectCommentByBookId(book_id);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("分页查询该书籍的评论信息成功");
        baseModel.setCode(0);
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel selectPageComment(String userName, int curr_page, int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page, page_size);
        List<Comment> list =  commentDao.selectPageComment(userName);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("查询所有书籍的类别信息成功");
        baseModel.setCode(0);
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel deleteComment(String ids) throws Exception {
        BaseModel baseModel = new BaseModel();
        String []id =ids.split(",");

        int row = commentDao.deleteComment(id);
        if (row <=0||ids.length()<=0) {
            throw new BusinessException(1032,"图书的评论信息删除操作失败");
        }
        baseModel.setMessage("删除"+row+"条数据成功");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel insertComment(Comment comment) throws Exception {
        BaseModel baseModel = new BaseModel();
        int row= commentDao.insertComment(comment);
        if (row<=0){
            throw new BusinessException(1033,"评论超时");
        }
        baseModel.setData(row);
        baseModel.setMessage("评论成功");
        baseModel.setCode(0);
        return baseModel;
    }
}
