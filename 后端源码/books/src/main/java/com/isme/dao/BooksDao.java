package com.isme.dao;

import com.isme.po.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksDao {
    List<Books> selectPageBooks(@Param("title") String title);
    int insertBooks(Books books);
    int deleteBooks(@Param("ids") String[] ids);
    int updateBooks(Books books);
    Books selectBooksById(@Param("id") int id);
    List<Books> selectBooksByCategoryId(@Param("category_id") String id,@Param("title") String title);
    int dianZan(@Param("id") int id,@Param("version") int version);
}
