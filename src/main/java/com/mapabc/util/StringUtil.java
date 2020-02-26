package com.mapabc.util;

/**
 * String 验证 util
 * @Author ke.han
 * @Date 2019/11/14 12:59
 **/
public class StringUtil {

    public static boolean isBlack(String param){
        if(param == null || "".equals(param.trim())){
            return true;
        }
        return false;
    }

    public static boolean isBlack(String... params){
        for(String param : params){
            if(isBlack(param)){
                return true;
            }
        }
        return false;
    }

}
