package com.isme.controller;

import com.isme.base.controller.BaseController;
import com.isme.base.vo.BaseModel;
import com.isme.service.LoginLogService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Api("登录日志模块")
@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

    @Autowired
    LoginLogService loginLogService;

    @ApiOperation(value = "分页查询登录日志",httpMethod = "GET",notes = "查询登录日志信息")
    @RequestMapping(value = "/selectPageLoginLog",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectPageLoginLog(@RequestParam(value = "user_name",required = false) String user_name,
                                        @RequestParam(value = "curr_page",required = true,defaultValue = "1") int curr_page,
                                        @RequestParam(value = "page_size",required = true,defaultValue = "10")  int page_size) throws Exception{
        return loginLogService.selectPageLoginLog(user_name,curr_page,page_size);
    }
}
