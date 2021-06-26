package com.isme.base.aop;

import com.isme.base.controller.BaseController;
import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.vo.BaseModel;
import com.isme.base.vo.BusinessException;
import com.isme.dao.StockDao;
import com.isme.po.Stock;
import com.isme.po.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Aspect
@Component
public class StockAspect extends BaseController {
    protected HttpSession session;
    protected HttpServletRequest request;
    protected BaseModel baseModel =new BaseModel();
    protected Logger log = Logger.getLogger(LoinLogAspect.class);

    @Autowired
    StockDao stockDao;
    @Pointcut("execution(* com.isme.controller..*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()&&@annotation(aopStock)")
    public void doBefore(JoinPoint joinPoint, AopStock aopStock)throws Exception{
        System.out.println("**********\n**********\n进入前置通知方法\n**********\n**********\n");
        //前置通知，获取切点内方法内的参数
        request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session=request.getSession();
        User currentUser =(User)session.getAttribute("currentUser");
        if (currentUser==null || currentUser.getState()!=0){
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
        }
        Object[] param=joinPoint.getArgs();
        int book_id = (Integer) param[0];
        int row = stockDao.selectStock(book_id,currentUser.getUser_id());
        if (row>0){
            throw new BusinessException(9009,"该书您已点过赞");
        }
    }

    @AfterReturning("pointCut()&&@annotation(aopStock)")
    public void doAfter(JoinPoint joinPoint,AopStock aopStock)throws Exception{
        if(aopStock.saveLog()){
            User currentUser =(User)session.getAttribute("currentUser");
            if (currentUser==null || currentUser.getState()!=0){
                throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT_ERROR);
            }
            Object[] param=joinPoint.getArgs();
            int book_id = (Integer) param[0];
            Stock stock = new Stock();
            stock.setBook_id(book_id);
            stock.setCreate_time(new Date());
            stock.setCreate_user_id(currentUser.getUser_id());
            int row = stockDao.insertStock(stock);
            if (row <= 0) {
                throw new BusinessException(9090,"点赞记录失败");
            }

            System.out.println("**********\n**********\n后置aop执行完成\n**********\n**********\n");
        }
    }
}
