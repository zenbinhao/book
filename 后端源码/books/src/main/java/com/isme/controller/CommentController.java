package com.isme.controller;

import com.isme.base.controller.BaseController;
import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.base.vo.ImgModel;
import com.isme.po.Comment;
import com.isme.po.User;
import com.isme.service.CommentService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Api("评论模块")
@RequestMapping("/comment")
@Controller
public class CommentController extends BaseController {
    @Autowired
    CommentService commentService;

    @ApiOperation(value = "通过book_id分页查询评论",httpMethod = "GET",notes = "分页查询指定书籍的所有评论")
    @ResponseBody
    @RequestMapping(value = "/selectCommentByBookId",method = RequestMethod.GET)
    public BaseModel selectCommentByBookId(@RequestParam(value = "book_id") int book_id,
                                           @RequestParam(value = "curr_page",defaultValue = "1",required = true) int curr_page,
                                           @RequestParam(value = "page_size",defaultValue = "10",required = true) int page_size) throws Exception{
        return commentService.selectCommentByBookId(book_id, curr_page, page_size);
    }

    @ApiOperation(value = "(有搜索)后台分页查询评论",httpMethod = "GET",notes = "模糊、分页查询用户评论信息")
    @ResponseBody
    @RequestMapping(value = "/selectPageComment",method = RequestMethod.GET)
    BaseModel selectPageComment(@RequestParam(value = "userName",required = false) String userName,
                                @RequestParam(value = "curr_page",defaultValue = "1",required = true) int curr_page,
                                @RequestParam(value = "page_size",defaultValue = "10",required = true)int page_size) throws Exception{
        return commentService.selectPageComment(userName, curr_page, page_size);
    }

    @ApiOperation(value = "批量删除(更新is_delete)",httpMethod = "POST",notes = "通过假删除留住数据")
    @ResponseBody
    @RequestMapping(value ="/deleteComment",method = RequestMethod.POST)
    BaseModel deleteComment(@RequestParam String ids, HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return commentService.deleteComment(ids);
    }

    @ApiOperation(value = "用户新增评论",httpMethod = "post",notes = "用户去新增评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "book_id",value = "图书id",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "content",value = "评论内容",paramType = "query",dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = "/insertComment",method = RequestMethod.POST)
    BaseModel insertComment(Comment comment,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=0){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        comment.setUser_id(currentUser.getUser_id());
        comment.setUser_name(currentUser.getUser_name());
        comment.setCreate_time(new Date());
        comment.setIs_delete(0);
        return commentService.insertComment(comment);
    }

    @RequestMapping(value = "/commentImgupload",method = RequestMethod.POST)
    @ResponseBody
    public ImgModel upload(@RequestParam("picture") MultipartFile[] picture, HttpServletRequest request , ImgModel imgModel) {
        // 获取服务器位置下面的路径
        String path = request.getSession().getServletContext().getRealPath("/img");
        //创建一个io流
        File file = new File(path);
        List<Object> list = new ArrayList<Object>();
        System.out.println("文件的保存路径:"+path);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("目录不存在,创建目录:"+file);
            file.mkdirs();
        }
        // 获取原始文件名称
        for (MultipartFile a:picture
             ) {
            String Filename = a.getOriginalFilename();
            System.out.println("原始文件名称:"+Filename);
            // 获取文件类型,以最后一个"."为标识
            String type = Filename.substring(Filename.lastIndexOf(".")+1);
            System.out.println("文件类型:"+type);
            // 获取文件名称
            String uuid= UUID.randomUUID().toString();
            // 设置文件新名称:当前时间+文件名称（不包含格式）
            Date date = new Date();
            String newFilename = uuid+"."+type;
            System.out.println("新文件名称:"+newFilename);
            //在指定路径创建文件夹
            File targetFile = new File(path, newFilename);
            //将文件保存在服务器指定位置.
            try{
                a.transferTo(targetFile);
                list.add("http://localhost:8080/book/img/"+newFilename);
            }catch (Exception e) {
                imgModel.setErrno(1);
                e.printStackTrace();
            }
        }
        imgModel.setErrno(0);
        imgModel.setData(list);
        return  imgModel;
    }
}
