package com.isme.service;

import com.isme.base.vo.BaseModel;
import com.isme.po.Record;

public interface RecordService {
    BaseModel selectRecord(String name,int curr_page,int page_size)throws Exception;
    BaseModel selectRecordById(int id,int curr_page,int page_size)throws Exception;
    BaseModel updateReturnRecord(Record record)throws Exception;
    BaseModel insertRecord(Record record)throws Exception;
    BaseModel deleteRecord(String ids)throws Exception;
    BaseModel selectRecordBookState(int id)throws Exception;
}
