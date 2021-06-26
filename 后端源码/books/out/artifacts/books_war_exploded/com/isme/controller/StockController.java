package com.isme.controller;

import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.po.User;
import com.isme.service.StockService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api("点赞记录表")
@Controller
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @ApiOperation(value = "查询点赞记录表",httpMethod = "GET")
    @RequestMapping(value = "/selectStock",method = RequestMethod.GET)
    @ResponseBody
    BaseModel selectStock(@RequestParam int book_id, @RequestParam int user_id, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=0){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return stockService.selectStock(book_id, user_id);
    }
}
