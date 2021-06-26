package com.isme.controller;

import com.isme.base.controller.BaseController;
import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.po.Collect;
import com.isme.po.User;
import com.isme.service.CollectService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api("收藏模块")
@RequestMapping("/collect")
@Controller
public class CollectController extends BaseController {
    @Autowired
    CollectService collectService;


    @ApiOperation(value = "查询个人收藏",httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/selectCollectById",method = RequestMethod.GET)
    BaseModel selectCollectById(@RequestParam(defaultValue = "1") int curr_page,
                                @RequestParam(defaultValue = "10") int page_size,
                                HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return collectService.selectCollectById(currentUser.getUser_id(),curr_page, page_size);
    }

    @ApiOperation(value = "点击收藏（新增）",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "book_id",name = "book_id",required = true,paramType = "query",dataType = "int")
    })
    @ResponseBody
    @RequestMapping(value = "/insertCollect",method = RequestMethod.POST)
    public BaseModel insertCollect(Collect collect,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        collect.setUser_id(currentUser.getUser_id());
        return collectService.insertCollect(collect);
    }

    @ApiOperation(value = "是否收藏",httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/selectIs",method = RequestMethod.GET)
    public BaseModel selectIs(@RequestParam int book_id,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return collectService.selectIs(currentUser.getUser_id(), book_id);
    }

    @ApiOperation(value = "取消收藏(真删)",httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/deleteCollect",method = RequestMethod.POST)
    public BaseModel deleteCollect(@RequestParam int book_id,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return collectService.deleteCollect(currentUser.getUser_id(),book_id);
    }
}
