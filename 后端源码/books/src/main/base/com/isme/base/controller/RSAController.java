package com.isme.base.controller;

import com.isme.base.utils.RSAUtils;
import com.isme.base.vo.BaseModel;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@Controller
@RequestMapping("/rsa")
@Scope("prototype")
public class RSAController {

    @ApiOperation(value = "获取登录时所需的公钥",httpMethod = "GET",notes = "查询")
    @RequestMapping(value = "/generateRSAJsKey",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel generateRSAJsKey(BaseModel baseModel, HttpServletRequest request){
        Map<String,Object> keyMap = RSAUtils.createKey();
        //取出公钥
        RSAPublicKey publicKey = (RSAPublicKey)keyMap.get(RSAUtils.PUBLIC_KEY);
        //取出私钥
        PrivateKey privateKey = (PrivateKey)keyMap.get(RSAUtils.PRIVATE_KEY);
        //将得到的私钥存入session中,然后获取到的公钥的公钥模板和公钥指数返回给前端
        HttpSession session = request.getSession();
        session.setAttribute(RSAUtils.PRIVATE_KEY,privateKey);
        String Modulus = publicKey.getModulus().toString(16);
        String exponent = publicKey.getPublicExponent().toString(16);
        String strSet = Modulus+";"+exponent;
        baseModel.setCode(0);
        baseModel.setMessage("获取公钥模板,公钥指数成功");
        baseModel.setData(strSet);
        return baseModel;
    }
}
