package com.isme.service;

import com.isme.base.vo.BaseModel;
import com.isme.po.Stock;

public interface StockService {
    BaseModel insertStock(Stock stock)throws Exception;
    BaseModel selectStock(int book_id,int user_id)throws Exception;
}
