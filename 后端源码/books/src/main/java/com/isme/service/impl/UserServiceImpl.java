package com.isme.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isme.base.utils.CheckUserLogin;
import com.isme.base.utils.MD5DES;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.UserDao;
import com.isme.po.User;
import com.isme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public BaseModel insertUser(User user) throws Exception {
        int rowAccount = userDao.selectByAccount(user.getAccount());
        if (rowAccount>0){
            throw new BusinessException(1018,"您输入的账号已存在");
        }
        int rowPhone = userDao.selectByPhone(user.getPhone());
        if (rowPhone>0){
            throw new BusinessException(1019,"您输入的手机号已存在");
        }
        BaseModel baseModel = new BaseModel();

        //md5加密存进数据库
        String password = user.getPassword();
        String md5Pwd = MD5DES.encrypt(password);
        user.setPassword(md5Pwd);

        int row = userDao.insertUser(user);
        if (row<=0){
            throw new BusinessException(9001,"用户信息注册失败,重新再试");
        }
        baseModel.setCode(0);
        baseModel.setData(row);
        baseModel.setMessage("恭喜您注册成功,将跳转到登录页面");
        return baseModel;
    }

    public BaseModel selectByLogin(String account, String password) throws Exception {
        BaseModel baseModel = new BaseModel();
        User user = userDao.selectByLogin(account,password);
        if (user.getState()==1){
            throw new BusinessException(2019,"您的账号已被封禁");
        }
        if(user==null){
            throw new BusinessException(2008,"你的账号或者密码错误,请重新再试");
        }
        baseModel.setMessage("登录成功,马上帮您跳转页面");
        baseModel.setData(user);
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel selectPageUser(String user_name,int curr_page, int page_size) throws Exception {
        BaseModel baseModel = new BaseModel();
        PageHelper.startPage(curr_page,page_size);
        List<User> list = userDao.selectPageUser(user_name);
        PageInfo pageInfo = new PageInfo(list,5);
        baseModel.setMessage("分页查询用户信息成功");
        baseModel.setData(pageInfo);
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel deleteUser(String ids)throws Exception {
        String []id =ids.split(",");

        BaseModel baseModel=new BaseModel();
        int row = userDao.deleteUser(id);
        if (row <=0) {
            throw new BusinessException(1012,"用户删除操作失败");
        }
        baseModel.setMessage("删除"+row+"条数据成功");
        baseModel.setCode(0);
        return baseModel;
    }

    public BaseModel updateUser(User user) throws Exception {
        CheckUserLogin checkUserLogin=new CheckUserLogin();
        if(user.getPassword().length()<6 || !checkUserLogin.getInvalue(user.getPassword())){
            throw new BusinessException(2006,"您的密码不能低于6位且需要包含字母数字的字符");
        }
        System.out.println("*************\n*************\n我进来了");
        int row = userDao.updateUser(user);
        BaseModel baseModel=new BaseModel();
        if(row<=0){
            throw new BusinessException(3013,"更新用户操作失败");
        }
        baseModel.setMessage("修改用户数据成功");
        baseModel.setCode(0);
        System.out.println("*************\n*************\n我要出去咯");
        return baseModel;
    }

    public BaseModel selectById(int id)throws Exception {
        BaseModel baseModel = new BaseModel();
        User user = userDao.selectById(id);
        if (user==null){
            throw new BusinessException(2008,"查询id:"+id+"的用户不存在");
        }
        baseModel.setData(user);
        baseModel.setCode(0);
        baseModel.setMessage("查询id为:"+id+"的用户成功");
        return baseModel;
    }

    public BaseModel selectByAccount(String account) throws Exception {
        BaseModel baseModel =new BaseModel();
        int row = userDao.selectByAccount(account);
        if (row>0){
            throw new BusinessException(7010,"该账户已经存在");
        }
        baseModel.setMessage("该账户可以使用");
        baseModel.setCode(0);
        return baseModel;
    }


}
