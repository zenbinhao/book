package com.isme.controller;

import com.isme.base.aop.AopOperation;
import com.isme.base.controller.BaseController;
import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.utils.CheckUserLogin;
import com.isme.base.utils.HttpUtils;
import com.isme.base.utils.MD5DES;
import com.isme.base.utils.RSAUtils;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.po.User;
import com.isme.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api("用户模块")
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    /*
    * 用户的注册接口
    *
    * */
    @ApiOperation(value = "用户新增(注册)",httpMethod = "POST",notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_name",value = "用户昵称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "account",value = "账户",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "phone",value = "手机号",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel insertUser(User user) throws Exception {
        user.setState(0);
        user.setCreate_time(new Date());
        user.setVersion(0);
        user.setIs_delete(0);
        if(user.getAccount()==null || user.getAccount().equals("")){
            throw new BusinessException(2001,"您的账号不能为空");
        }
        if(user.getPassword()==null || user.getPassword().equals("")){
            throw new BusinessException(2002,"您的密码不能为空");
        }
        if(user.getUser_name()==null || user.getUser_name().equals("")){
            throw new BusinessException(2003,"您的昵称不能为空");
        }
        if(user.getPhone()==null || user.getPhone().equals("")){
            throw new BusinessException(2004,"您的手机号不能为空");
        }
        CheckUserLogin checkUserLogin=new CheckUserLogin();
        if(user.getAccount().length()<6 || !checkUserLogin.getInvalue(user.getAccount())){
            throw new BusinessException(2005,"您的账号不能低于6位且需要包含字母数字的字符");
        }
        if(user.getPassword().length()<6){
            throw new BusinessException(2006,"您的密码不能低于6位");
        }
        if(!checkUserLogin.getPhoneCheck(user.getPhone())){
            throw new BusinessException(2007,"您的手机号格式输入有误,请重新输入");
        }
        return userService.insertUser(user);
    }


    /*
     * 用户的登录接口
     *
     * */
    @AopOperation
    @ApiOperation(value = "查询(登录)",httpMethod = "POST",notes = "查询用户信息(登录)")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "account",value = "账户",paramType = "query",dataType = "String"),
//            @ApiImplicitParam(name = "password",value = "密码",paramType = "query",dataType = "String")
//    })
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel selectByLogin(@RequestParam("account") String account,@RequestParam("password")  String password, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        //解密成明文
        String whitePassword= RSAUtils.sessionDecryptHexStr(session,password);

        String md5Pwd = MD5DES.encrypt(whitePassword);

        BaseModel baseModel = userService.selectByLogin(account,md5Pwd);
        User user=(User)baseModel.getData();
        if (user.getState() ==1) {
            throw new BusinessException(9901,"您的账号已被封禁");
        }
        session.setAttribute("currentUser",user);
        return baseModel;
    }


    @ApiOperation(value = "查询(分页查询所有用户信息)",httpMethod = "GET",notes = "分页查询用户信息")
    @RequestMapping(value = "/selectPageUser",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectPageUser(@RequestParam(value = "user_name",required = false) String user_name,
                                    @RequestParam(value = "curr_page",required = true,defaultValue = "1") int curr_page,
                                    @RequestParam(value = "page_size",defaultValue = "10",required = true) int page_size)throws Exception{
        return userService.selectPageUser(user_name,curr_page,page_size);
    }




    @ApiOperation(value = "删除(假删用户信息)",httpMethod = "POST",notes = "删除用户信息(修改is_delete)")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel deleteUser(@RequestParam(value = "ids") String ids,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        BaseModel baseModel=userService.deleteUser(ids);
        return baseModel;
    }




    @ApiOperation(value = "修改(修改用户信息)",httpMethod = "POST",notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id",value = "用户id",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "version",value = "版本号",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "user_name",value = "用户昵称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "state",value = "状态",paramType = "query",dataType = "int",defaultValue = "0",required = true)
    })
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel updateUser(User user,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }

        user.setUpdate_time(new Date());
        user.setUpdate_user_id(currentUser.getUser_id());
        user.setUpdate_user_name(currentUser.getUser_name());
        return userService.updateUser(user);
    }


    @ApiOperation(value = "通过id查询",httpMethod = "GET",notes = "查询id为需要的用户,用户回显操作")
    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectById(@RequestParam(value = "id") int id,HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=2){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return userService.selectById(id);
    }

    @ApiOperation(value = "通过session中拿到用户id查询",httpMethod = "GET")
    @RequestMapping(value = "/selectPersonById",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectPersonById(HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        return userService.selectById(currentUser.getUser_id());
    }

    @RequestMapping(value = "/destroy")
    @ResponseBody
    public String destroy(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        session.removeAttribute("currentUser");
        return "销毁成功";
    }

    @RequestMapping(value = "/phoneCode",method = RequestMethod.POST)
    public String phoneCode(@RequestParam String phone,HttpServletRequest request){
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";

        int code = (int) ((Math.random()*9+1)*1000);
        HttpSession session = request.getSession();
        String yzm = code +"";
        session.setAttribute(phone,yzm);
        String appcode = "2ed1156af3c946629151d593a7736975";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("param", "code:"+yzm);
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();

        String result = null;
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            result = response.toString();
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(value = "/rePhoneCode",method = RequestMethod.POST)
    @ResponseBody
    public Boolean rePhoneCode(@RequestParam String phone,@RequestParam String code,HttpServletRequest request){
        HttpSession session = request.getSession();
        String reCode = (String) session.getAttribute(phone);
        if (code.equals(reCode)){
            session.removeAttribute("phone");
            return true;
        }
        return false;
    }


    @ApiOperation(value = "查询",httpMethod = "GET",notes = "校验是否被注册过该账号")
    @RequestMapping(value = "/registerSelectAccount",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel registerSelectAccount(@RequestParam String account) throws Exception {
        CheckUserLogin checkUserLogin=new CheckUserLogin();
        if(account.length()<6 || !checkUserLogin.getInvalue(account)){
            throw new BusinessException(2005,"您的账号不能低于6位且需要包含字母数字的字符");
        }
        return userService.selectByAccount(account);
    }
}
