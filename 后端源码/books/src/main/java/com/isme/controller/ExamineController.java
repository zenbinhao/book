package com.isme.controller;

import com.isme.base.controller.BaseController;
import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.po.Examine;
import com.isme.po.User;
import com.isme.service.ExamineService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Api("图书审核模块")
@Controller
@RequestMapping(value = "/examine")
public class ExamineController extends BaseController {

    @Autowired
    ExamineService examineService;


    @ApiOperation(value = "分页查询图书进购审核信息",httpMethod = "GET",notes = "查询图书进购审核信息")
    @RequestMapping(value = "/selectPageExamine",method = RequestMethod.GET)
    @ResponseBody
    BaseModel selectPageExamine(@RequestParam(value = "purchase_book",required = false) String purchase_book,
                                @RequestParam(value = "curr_page",required = true,defaultValue = "1") int curr_page,
                                @RequestParam(value = "page_size",required = true,defaultValue = "10") int page_size)throws Exception{
        return examineService.selectPageExamine(purchase_book,curr_page,page_size);
    }



    @ApiOperation(value = "图书进购审核信息添加",httpMethod = "POST",notes = "添加图书进购审核信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "purchase_book",value = "进购图书名称",paramType = "query",dataType = "String",required = true)
    })
    @RequestMapping(value = "/insertExamine",method = RequestMethod.POST)
    @ResponseBody
    BaseModel insertExamine(Examine examine, HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        examine.setCreate_time(new Date());
        examine.setIs_delete(0);
        examine.setVersion(0);
        examine.setCreate_user_id(currentUser.getUser_id());
        examine.setCreate_user_name(currentUser.getUser_name());
        return examineService.insertExamine(examine);
    }



    @ApiOperation(value = "批量删除图书进购审核信息",httpMethod = "POST",notes = "假删除真修改is_delete")
    @RequestMapping(value = "/deleteExamine",method = RequestMethod.POST)
    @ResponseBody
    BaseModel deleteExamine(@RequestParam(value = "ids") String ids,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return examineService.deleteExamine(ids);
    }


    @ApiOperation(value = "修改图书进购审核信息",httpMethod = "POST",notes = "修改图书进购审核信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "purchase_book",value = "进购图书名称",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "examine_state",value = "审核状态",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "examine_id",value = "审核id",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "version",value = "版本号",paramType = "query",dataType = "int",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/updateExamine",method = RequestMethod.POST)
    BaseModel updateExamine(Examine examine,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        examine.setUpdate_time(new Date());
        examine.setUpdate_user_id(currentUser.getUser_id());
        examine.setUpdate_user_name(currentUser.getUser_name());
        return examineService.updateExamine(examine);
    }

    @ApiOperation(value = "通过id查询",httpMethod = "GET",notes = "查询id为需要的进购审核信息,修改回显操作")
    @RequestMapping(value = "/selectExamineById",method = RequestMethod.GET)
    @ResponseBody
    BaseModel selectExamineById(@RequestParam(value = "id") int id,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return examineService.selectExamineById(id);
    }
}
