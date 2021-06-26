package com.isme.base.utils;

import com.isme.base.vo.BusinessException;

import java.util.regex.Pattern;

public class CheckUserLogin {
    //密码复杂度校验
    /*    */
    public boolean getInvalue(String pw){
        for(int i=0;i<pw.length();i++){
            if(Character.isDigit(pw.charAt(i))&& Pattern.compile("(?i)[A-Za-z]").matcher(pw).find()){
                return true;
            }
        }
        return false;
    }
    public boolean getPhoneCheck(String phone){
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (!Pattern.matches(regex, phone)) {
            return false;
        }
        return true;
    }
}
