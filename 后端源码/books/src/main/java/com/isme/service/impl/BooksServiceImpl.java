package com.isme.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.BooksDao;
import com.isme.po.Books;
import com.isme.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksDao booksDao;

    public BaseModel selectPageBooks(String title, int curr_page, int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page, page_size);
        List<Books> list =  booksDao.selectPageBooks(title);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("查询所有书籍信息成功");
        baseModel.setCode(0);
        baseModel.setData(pageInfo);
        return baseModel;
    }


    public BaseModel insertBooks(Books books) throws Exception {
        BaseModel baseModel = new BaseModel();
        int row = booksDao.insertBooks(books);
        if (row<=0){
            throw new BusinessException(1017,"图书信息添加失败");
        }
        baseModel.setCode(0);
        baseModel.setData(row);
        baseModel.setMessage("图书信息添加成功");
        return baseModel;
    }

    public BaseModel deleteBooks(String ids)throws Exception{
        BaseModel baseModel = new BaseModel();
        String []id =ids.split(",");

        int row = booksDao.deleteBooks(id);
        if (row <=0||ids.length()<=0) {
            throw new BusinessException(1112,"图书删除操作失败");
        }
        baseModel.setMessage("删除"+row+"条数据成功");
        baseModel.setCode(0);
        return baseModel;
    }


    public BaseModel updateBooks(Books books)throws Exception{
        int row = booksDao.updateBooks(books);
        BaseModel baseModel=new BaseModel();
        if(row<=0){
            throw new BusinessException(1113,"修改图书信息操作失败");
        }
        baseModel.setMessage("修改图书信息成功");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel selectBooksById(int id) throws Exception {
        BaseModel baseModel = new BaseModel();
        Books books = booksDao.selectBooksById(id);
        if (books==null){
            throw new BusinessException(2005,"查询id:"+id+"的图书不存在");
        }
        baseModel.setData(books);
        baseModel.setCode(0);
        baseModel.setMessage("查询id为:"+id+"的图书成功");
        return baseModel;
    }

    public BaseModel selectBooksByCategoryId(String id,String title, int curr_page, int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page, page_size);
        List<Books> list =  booksDao.selectBooksByCategoryId(id,title);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("根据类别查询所有书籍信息成功");
        baseModel.setCode(0);
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel dianZan(int id, int version) throws Exception {
        BaseModel baseModel = new BaseModel();
        int row = booksDao.dianZan(id, version);
        if (row<=0) {
            throw new BusinessException(5001,"点赞超时请重新再试");
        }
        baseModel.setCode(0);
        baseModel.setMessage("点赞成功");
        return baseModel;
    }
}
