package com.isme.service;

import com.isme.base.vo.BaseModel;

public interface LoginLogService {
    BaseModel selectPageLoginLog(String user_name,int curr_page,int page_size)throws Exception;
}
