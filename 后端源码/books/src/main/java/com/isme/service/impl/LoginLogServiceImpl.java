package com.isme.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.LoginLogDao;
import com.isme.po.LoginLog;
import com.isme.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogDao loginLogDao;

    public BaseModel selectPageLoginLog(String user_name,int curr_page, int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page, page_size);
        List<LoginLog> list =  loginLogDao.selectPageLoginLog(user_name);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("查询登录日志成功");
        baseModel.setCode(0);
        baseModel.setData(pageInfo);
        return baseModel;
    }
}
