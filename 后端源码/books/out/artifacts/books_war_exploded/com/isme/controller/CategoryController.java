package com.isme.controller;


import com.isme.base.controller.BaseController;
import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.po.Category;
import com.isme.po.User;
import com.isme.service.CategoryService;
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

@Api("图书类别模块")
@RequestMapping("/category")
@Controller
public class CategoryController extends BaseController {
    @Autowired
    CategoryService categoryService;



    @ApiOperation(value = "分页查询图书类别信息",httpMethod = "GET",notes = "查询图书类别信息")
    @RequestMapping(value = "/selectPageCategory",method = RequestMethod.GET)
    @ResponseBody
    BaseModel selectPageCategory(@RequestParam(value = "name",required = false) String categoryName,
                                 @RequestParam(value = "curr_page",required = true,defaultValue = "1") int curr_page,
                                 @RequestParam(value = "page_size",required = true,defaultValue = "10") int page_size)throws Exception{
        return categoryService.selectPageCategory(categoryName,curr_page,page_size);
    }





    @ApiOperation(value = "图书类别信息添加",httpMethod = "POST",notes = "添加图书类别信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category_name",value = "图书类别名称",paramType = "query",dataType = "String",required = true)
    })
    @RequestMapping(value = "/insertCategory",method = RequestMethod.POST)
    @ResponseBody
    BaseModel insertCategory(Category category, HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        category.setCreate_time(new Date());
        category.setIs_delete(0);
        category.setVersion(0);
        category.setCreate_user_id(currentUser.getUser_id());
        category.setCreate_user_name(currentUser.getUser_name());
        return categoryService.insertCategory(category);
    }



    @ApiOperation(value = "批量删除图书类别信息",httpMethod = "POST",notes = "假删除真修改is_delete")
    @RequestMapping(value = "/deleteCategory",method = RequestMethod.POST)
    @ResponseBody
    BaseModel deleteCategory(@RequestParam(value = "ids") String ids,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return categoryService.deleteCategory(ids);
    }


    @ApiOperation(value = "修改图书类别信息",httpMethod = "POST",notes = "修改图书类别的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category_name",value = "图书类别名称",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "category_id",value = "分类id",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "version",value = "版本号",paramType = "query",dataType = "int",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/updateCategory",method = RequestMethod.POST)
    BaseModel updateCategory(Category category,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        category.setUpdate_time(new Date());
        category.setUpdate_user_id(currentUser.getUser_id());
        category.setUpdate_user_name(currentUser.getUser_name());
        return categoryService.updateCategory(category);
    }



    @ApiOperation(value = "通过id查询",httpMethod = "GET",notes = "查询id为需要的类别信息,修改回显操作")
    @RequestMapping(value = "/selectCategoryById",method = RequestMethod.GET)
    @ResponseBody
    BaseModel selectCategoryById(@RequestParam(value = "id") int id,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return categoryService.selectCategoryById(id);
    }

}
