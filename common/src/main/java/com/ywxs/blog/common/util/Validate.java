package com.ywxs.blog.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static Pattern NUMBER_PATTERN = Pattern.compile("^1[3456789]\\d{9}$");
    public  static Pattern EMAIL_PATTERN = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag;
        try {
            Matcher matcher = NUMBER_PATTERN.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            throw new RuntimeException("手机号格式有误！");
        }
        return flag;
    }

    public static  boolean checkEmail(String email){
        boolean flag;
        try {
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            throw new RuntimeException("邮箱格式有误！");
        }
        return flag;
    }
}
