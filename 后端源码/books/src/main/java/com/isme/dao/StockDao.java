package com.isme.dao;

import com.isme.po.Stock;
import org.apache.ibatis.annotations.Param;

public interface StockDao {
    int insertStock(Stock stock);
    int selectStock(@Param("book_id") int book_id,@Param("user_id") int user_id);
}