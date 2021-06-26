package com.isme.controller;

import com.isme.base.aop.AopStock;
import com.isme.base.controller.BaseController;
import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.po.Books;
import com.isme.po.User;
import com.isme.service.BooksService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.hibernate.validator.internal.util.Contracts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api("图书模块")
@RequestMapping("/books")
@Controller
public class BooksController extends BaseController {
    private static final java.util.UUID UUID = java.util.UUID.randomUUID();
    @Autowired
    BooksService booksService;

    @ApiOperation(value = "分页查询书籍信息",httpMethod = "GET",notes = "查询书籍信息")
    @RequestMapping(value = "/selectPageBooks",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectPageBooks(@RequestParam(value = "title",required = false) String title,
                                     @RequestParam(value = "curr_page",required = true,defaultValue = "1") int curr_page,
                                     @RequestParam(value = "page_size",required = true,defaultValue = "10")  int page_size) throws Exception{
        return booksService.selectPageBooks(title,curr_page,page_size);
    }


    @ApiOperation(value = "图书信息添加",httpMethod = "POST",notes = "添加图书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "book_title",value = "图书标题",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "introduce",value = "图书简介",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "author",value = "作者",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "book_picture",value = "图书图片",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "stock",value = "点赞数",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "count",value = "数量",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "category_id",value = "分类id",paramType = "query",dataType = "int",required = true)
    })
    @RequestMapping(value = "/insertBooks",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel insertBooks(Books books, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        books.setCreate_time(new Date());
        books.setIs_delete(0);
        books.setVersion(0);
        books.setCreate_user_id(currentUser.getUser_id());
        books.setCreate_user_name(currentUser.getUser_name());
        return booksService.insertBooks(books);
    }



    @ApiOperation(value = "批量删除图书信息",httpMethod = "POST",notes = "假删除真修改is_delete")
    @RequestMapping(value = "/deleteBooks",method = RequestMethod.POST)
    @ResponseBody
    BaseModel deleteBooks(@RequestParam(value = "ids",required = true) String ids,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return booksService.deleteBooks(ids);
    }

    @ApiOperation(value = "修改图书信息",httpMethod = "POST",notes = "修改图书的信息")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "book_id",value = "图书id",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "version",value = "版本号",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "book_title",value = "图书标题",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "introduce",value = "图书简介",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "author",value = "作者",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "book_picture",value = "图书图片",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "stock",value = "点赞数",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "count",value = "数量",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "category_id",value = "分类id",paramType = "query",dataType = "int",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/updateBooks",method = RequestMethod.POST)
    BaseModel updateBooks(Books books,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        books.setUpdate_time(new Date());
        books.setUpdate_user_id(currentUser.getUser_id());
        books.setUpdate_user_name(currentUser.getUser_name());
        return booksService.updateBooks(books);
    }


    @ApiOperation(value = "通过id查询",httpMethod = "GET",notes = "查询id为需要的图书,图书修改回显操作")
    @RequestMapping(value = "/selectBooksById",method = RequestMethod.GET)
    @ResponseBody
    BaseModel selectBooksById(@RequestParam(value = "id") int id)throws Exception{
        return booksService.selectBooksById(id);
    }



    @ApiOperation(value = "通过category_id查询",httpMethod = "GET",notes = "查询category_id为需要的图书,回显页面操作")
    @RequestMapping(value = "/selectBooksByCategoryId",method = RequestMethod.GET)
    @ResponseBody
    BaseModel selectBooksByCategoryId(@RequestParam(value = "category_id",required = false) String id,
                                      @RequestParam(value = "title",required = false) String title,
                                      @RequestParam(value = "curr_page",required = true,defaultValue = "1") int curr_page,
                                      @RequestParam(value = "page_size",required = true,defaultValue = "10")  int page_size)throws Exception{
        return booksService.selectBooksByCategoryId(id,title,curr_page,page_size);
    }





    @RequestMapping(value = "/imgupload",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request , BaseModel baseModel) {
                        // 获取服务器位置下面的路径
        String path = request.getSession().getServletContext().getRealPath("/img");
        //创建一个io流
        File file = new File(path);

        System.out.println("文件的保存路径:"+path);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("目录不存在,创建目录:"+file);
            file.mkdirs();
        }
        // 获取原始文件名称
        String Filename = picture.getOriginalFilename();
        System.out.println("原始文件名称:"+Filename);
        // 获取文件类型,以最后一个"."为标识
        String type = Filename.substring(Filename.lastIndexOf(".")+1);
        System.out.println("文件类型:"+type);
        String uuid = UUID.randomUUID().toString();
        String newFilename = uuid+"."+type;
        System.out.println("新文件名称:"+newFilename);
        //在指定路径创建文件夹
        File targetFile = new File(path, newFilename);
        //将文件保存在服务器指定位置
        try{
            picture.transferTo(targetFile);
            baseModel.setCode(0);
            baseModel.setData("/img/"+newFilename);
            baseModel.setMessage("上传成功!");
        }catch (Exception e) {
            baseModel.setCode(1);
            baseModel.setMessage("上传失败!");
            e.printStackTrace();

        }

        return  baseModel;
    }

    @ApiOperation(value = "点赞",httpMethod = "GET")
    @Transactional
    @AopStock
    @RequestMapping(value = "/dianZan",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel dianZan(@RequestParam int id,@RequestParam int version,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=0){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return booksService.dianZan(id,version);
    }

}
