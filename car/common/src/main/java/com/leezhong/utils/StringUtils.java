package com.leezhong.utils;

public class StringUtils {

    public static boolean isBlank(String str){
        if(str==null||"".equals(str.trim())){
            return true;
        }
        return false;
    }
}
