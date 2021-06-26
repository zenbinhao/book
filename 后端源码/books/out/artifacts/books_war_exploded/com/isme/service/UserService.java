package com.isme.service;

import com.isme.base.vo.BaseModel;
import com.isme.po.User;

public interface UserService {

    BaseModel insertUser(User user) throws Exception;
    BaseModel selectByLogin(String account,String password) throws Exception;
    BaseModel selectPageUser(String user_name,int curr_page,int page_size) throws Exception;
    BaseModel deleteUser(String ids)throws Exception;
    BaseModel updateUser(User user)throws Exception;
    BaseModel selectById(int id)throws Exception;
    BaseModel selectByAccount(String account)throws Exception;
}
