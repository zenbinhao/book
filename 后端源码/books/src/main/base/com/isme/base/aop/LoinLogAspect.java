package com.isme.base.aop;

import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.LoginLogDao;
import com.isme.po.LoginLog;
import com.isme.po.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Aspect
@Component
public class LoinLogAspect {
    protected HttpSession session;
    protected HttpServletRequest request;
    protected BaseModel baseModel =new BaseModel();
    protected Logger log = Logger.getLogger(LoinLogAspect.class);

    @Autowired
    LoginLogDao loginLogDao;
    @Pointcut("execution(* com.isme.controller..*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()&&@annotation(operation)")
    public void doBefore(JoinPoint joinPoint,AopOperation operation)throws Exception{
        System.out.println("**********\n**********\n进入前置通知方法\n**********\n**********\n");
        //前置通知，获取切点内方法内的参数
        request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        session=request.getSession();
        Object[] params=joinPoint.getArgs();
        for (int i=0;i<params.length;i++){
            if(params[i].getClass()==BaseModel.class){
                baseModel=(BaseModel)params[i];
            }
        }
    }

    @After("pointCut()&&@annotation(operation)")
    public void doAfter(JoinPoint joinPoint,AopOperation operation)throws Exception{
        if(operation.saveLog()){
            User currentUser = (User) session.getAttribute("currentUser");
            LoginLog loginLog= new LoginLog();
            loginLog.setUser_id(currentUser.getUser_id());
            loginLog.setUser_account(currentUser.getAccount());
            loginLog.setUser_name(currentUser.getUser_name());
            loginLog.setLogin_date(new Date());
            loginLog.setIp(request.getRemoteAddr());
            int row = loginLogDao.insertLoginLog(loginLog);
            if (row<=0){
                new BusinessException(1111,"新增登录日志失败");
            }
            System.out.println("**********\n**********\n后置aop执行完成\n**********\n**********\n");
        }
    }
}
