package com.isme.controller;

import com.isme.base.controller.BaseController;
import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.utils.SendEmailListener;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.po.Record;
import com.isme.po.User;
import com.isme.service.RecordService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Api("借还书记录模块")
@RequestMapping("/record")
@Controller
public class RecordController extends BaseController {

    @Autowired
    RecordService recordService;

    @ApiOperation(value = "分页查询用户借还书记录",httpMethod = "GET",notes = "后台管理员分页查询用户借书还书记录")
    @ResponseBody
    @RequestMapping(value = "/selectPageRecord",method = RequestMethod.GET)
    BaseModel selectPageRecord(@RequestParam(value = "name",required = false) String name,
                               @RequestParam(value = "curr_page",required = true,defaultValue = "1") int curr_page,
                               @RequestParam(value = "page_size",required = true,defaultValue = "10") int page_size)throws Exception{
        return recordService.selectRecord(name,curr_page,page_size);
    }


    @ApiOperation(value = "分页查询本用户的记录",httpMethod = "GET",notes = "用户分页查询用户借书还书记录")
    @ResponseBody
    @RequestMapping(value = "/selectPageRecordById",method = RequestMethod.GET)
    public BaseModel selectRecordById(@RequestParam(value = "curr_page",defaultValue = "1") int curr_page,
                                      @RequestParam(value = "page_size",defaultValue = "10") int page_size,
                                      HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return recordService.selectRecordById(currentUser.getUser_id(), curr_page, page_size);
    }



    @ApiOperation(value = "修改(还书)",httpMethod = "POST",notes = "后台管理员操作还书操作 修改书籍状态")
    @ResponseBody
    @RequestMapping(value = "/updateReturnRecord",method = RequestMethod.POST)
    BaseModel updateReturnRecord(@RequestParam(value = "record_id") int record_id,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        Record record = new Record();
        record.setBook_state(1);
        record.setReturn_time(new Date());
        record.setUpdate_user_id(currentUser.getUser_id());
        record.setUpdate_user_name(currentUser.getUser_name());
        record.setRecord_id(record_id);
        return recordService.updateReturnRecord(record);
    }

    @Transactional
    @ApiOperation(value = "借书,新增操作",httpMethod = "POST",notes = "用户借书操作 新增借书还书记录")
    @ResponseBody
    @RequestMapping(value = "/insertRecord",method = RequestMethod.POST)
    BaseModel insertRecord(@RequestParam int book_id,
                           @RequestParam String book_title, HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=0){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }



        Record record = new Record();
        record.setBook_id(book_id);
        record.setBook_title(book_title);
        record.setBorrow_time(new Date());
        record.setUser_id(currentUser.getUser_id());
        record.setUser_name(currentUser.getUser_name());
        record.setIs_delete(0);
        record.setBook_state(0);
        return recordService.insertRecord(record);
    }


    @ApiOperation(value = "删除操作",httpMethod = "POST",notes = "修改is_delete字段 假删除")
    @ResponseBody
    @RequestMapping(value = "/deleteRecord",method = RequestMethod.POST)
    public BaseModel deleteRecord(@RequestParam(value = "ids") String ids,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return recordService.deleteRecord(ids);
    }

    @ApiOperation(value = "发送邮件验证码",httpMethod = "GET")
    @RequestMapping(value = "/sendEmail",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel sendEmail(@RequestParam String email){
        BaseModel baseModel = new BaseModel();
        SendEmailListener sendEmailListener = new SendEmailListener();
        String code = sendEmailListener.sendEmail(email);
        baseModel.setMessage("邮箱已发送");
        baseModel.setCode(0);
        baseModel.setData(code);
        return baseModel;
    }

    @ApiOperation(value = "session找是否有未还的书",httpMethod = "GET")
    @RequestMapping(value = "/selectRecordBookState",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectRecordBookState(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return recordService.selectRecordBookState(currentUser.getUser_id());
    }
}
