package com.isme.service;

import com.isme.base.vo.BaseModel;
import com.isme.po.Collect;

public interface CollectService {
    BaseModel selectCollectById(int user_id, int curr_page, int page_size)throws Exception;
    BaseModel insertCollect(Collect collect)throws Exception;
    BaseModel selectIs(int user_id,int book_id)throws Exception;
    BaseModel deleteCollect(int user_id,int book_id)throws Exception;
}
