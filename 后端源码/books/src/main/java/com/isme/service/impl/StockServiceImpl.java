package com.isme.service.impl;

import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.StockDao;
import com.isme.po.Stock;
import com.isme.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockDao stockDao;

    public BaseModel insertStock(Stock stock)throws Exception {
        BaseModel baseModel = new BaseModel();
        int row = stockDao.insertStock(stock);
        if (row <= 0){
            throw new BusinessException(5005,"点赞记录新增失败");
        }
        baseModel.setMessage("点赞记录新增成功！！");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel selectStock(int book_id, int user_id)throws Exception {
        BaseModel baseModel = new BaseModel();
        int row = stockDao.selectStock(book_id,user_id);
        if ( row >0 ){
            throw new BusinessException(5006,"该账户已经点过赞");
        }
        baseModel.setCode(0);
        baseModel.setMessage("该用户没有点过赞");
        return baseModel;
    }
}
