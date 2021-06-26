package com.isme.service;

import com.isme.base.vo.BaseModel;
import com.isme.po.Books;

public interface BooksService {
    BaseModel selectPageBooks(String title,int curr_page,int page_size)throws Exception;
    BaseModel insertBooks(Books books)throws Exception;
    BaseModel deleteBooks(String ids)throws Exception;
    BaseModel updateBooks(Books books)throws Exception;
    BaseModel selectBooksById(int id)throws Exception;
    BaseModel selectBooksByCategoryId(String id,String title, int curr_page, int page_size)throws Exception;
    BaseModel dianZan(int id,int version)throws Exception;
}
