package com.isme.base.controller;

import com.isme.base.em.ErrorMessageEnum;
import com.isme.base.vo.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    //统一处理  业务异常
    @ExceptionHandler(BusinessException.class)
    public BusinessException businessException(BusinessException ex){
        return ex;
    }
    @ExceptionHandler(Exception.class)
    public BusinessException businessException(Exception e){
        BusinessException businessException = new BusinessException(ErrorMessageEnum.SYS_ERROR);
        return businessException;
    }
}
