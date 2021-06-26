package com.isme.dao;

import com.isme.po.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginLogDao {
    int insertLoginLog(LoginLog loginLog);
    List<LoginLog> selectPageLoginLog(@Param("name") String user_name);
}
