package com.ywxs.blog.common.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 ** @date 2020/5/25 11:51
 **/
public class IdUtils {

    public  static final  int aaa = 10001;
    public  static String getSnowflakeId() {
        return IdUtil.getSnowflake(1, 1).nextIdStr();
    }

    public static String getId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Convert.toStr(principal);
    }
    public static String getUserName() {
        Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return Convert.toStr(credentials);
    }

}
