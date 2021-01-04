package com.ywxs.blog.common.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 ** @date 2020/6/3 21:14
 **/
public class DateUtils {

    /**
     * 当日0
     *
     * @return
     */
    public static Long todayZeroTimeMillis() {
        Long time = System.currentTimeMillis();  //当前时间的时间戳
        long zero = time / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return zero;

    }

    /**
     * 当日23.59
     *
     * @return
     */
    public static Long todayTwentyThreeTimeMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        long tt = calendar.getTime().getTime();
        return tt;
    }


    public static Long todayEight() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 8, 00, 00);
        long tt = calendar.getTime().getTime();
        return tt;
    }

    public static Long weekBeforeEight() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - 7, 8, 00, 00);
        long tt = calendar.getTime().getTime();
        return tt;
    }

    public static Long monthBeforeEight() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - 30, 8, 00, 00);
        long tt = calendar.getTime().getTime();
        return tt;
    }

    public static Long tomorrowSeven() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1, 7, 59, 59);
        long tt = calendar.getTime().getTime();
        return tt;
    }

    public static Long sevenBeforeTimeMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - 7, 0, 0, 0);
        long tt = calendar.getTime().getTime();
        return tt;
    }

    public static Long thirtyBeforeTimeMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - 30, 0, 0, 0);
        long tt = calendar.getTime().getTime();
        return tt;
    }
}
