package com.isme.service;

import com.isme.base.vo.BaseModel;
import com.isme.po.Examine;

public interface ExamineService {
    BaseModel selectPageExamine(String purchase_book, int curr_page, int page_size)throws Exception;
    BaseModel insertExamine(Examine examine)throws Exception;
    BaseModel deleteExamine(String ids)throws Exception;
    BaseModel updateExamine(Examine examine)throws Exception;
    BaseModel selectExamineById(int id)throws Exception;
}
