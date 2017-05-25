package com.kerry.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static final String DEFAULT_PATTERN = "yyyyMMddHHmmss";

    private static final String[] WEEK = {"星期一", "星期二", "星期三", "星期四", "星期五",
            "星期六", "星期日"};

    /**
     * @param time
     * @param pattern
     * @return
     */
    public static String timestamp2str(Timestamp time, String pattern) {
        if (time == null) {
            throw new IllegalArgumentException("Timestamp is null");
        }
        if (pattern != null && !"".equals(pattern)) {
            if (!"yyyyMMddHHmmss".equals(pattern)
                    && !"yyyy-MM-dd HH:mm:ss".equals(pattern)
                    && !"yyyy-MM-dd".equals(pattern)
                    && !"MM/dd/yyyy".equals(pattern)) {
                throw new IllegalArgumentException("Date format [" + pattern + "] is invalid");
            }
        } else {
            pattern = DEFAULT_PATTERN;
        }

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTime(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(cal.getTime());
    }

    public static Date str2Date(String timeStr, String pattern) {
        if (timeStr == null) {
            throw new IllegalArgumentException("Timestamp is null");
        }
        if (pattern != null && !"".equals(pattern)) {
            if (!"yyyyMMddHHmmss".equals(pattern)
                    && !"yyyy-MM-dd HH:mm:ss".equals(pattern)
                    && !"MM/dd/yyyy HH:mm:ss".equals(pattern)
                    && !"yyyy-MM-dd".equals(pattern)
                    && !"MM/dd/yyyy".equals(pattern)
                    && !"HH:mm".equals(pattern)) {
                throw new IllegalArgumentException("Date format [" + pattern + "] is invalid");
            }
        } else {
            pattern = DEFAULT_PATTERN;
        }
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            d = sdf.parse(timeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Timestamp str2Timestamp(String timeStr, String pattern) {
        Date d = str2Date(timeStr, pattern);
        Timestamp result = new Timestamp(d.getTime());
        return result;
    }

    /**
     * 根据日期计算当前星期几
     *
     * @param pTime 日期
     * @return 星期
     * @throws Exception
     */
    public static String dayForWeek(String pTime) {

        int dayForWeek = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(format.parse(pTime));
            dayForWeek = 0;
            if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                dayForWeek = 7;
            } else {
                dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WEEK[dayForWeek - 1];
    }

    /**
     * 获取日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getDate(Date date, String pattern) {
        if (pattern == null || pattern.equals("")) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    /**
     * 日期加减
     *
     * @param date
     * @param pattern
     * @param day
     * @return
     */
    public static String dateAddDays(Date date, String pattern, int day) {
        if (pattern == null || pattern.equals("")) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, day); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
        date = c.getTime(); //结果
        return sf.format(date);
    }

    /**
     * 比较两个日期的大小
     *
     * @param releDate 放号时间
     * @param nowDate  当前时间
     * @return 放号时间大于当前时间返回true 反之false
     * @throws Exception
     */
    public static boolean compare_date(String releDate, String nowDate, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            Date d1 = df.parse(releDate);
            Date d2 = df.parse(nowDate);
            if (d1.getTime() > d2.getTime()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 字符串转为DATE
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date strToDate(String dateStr, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将微信返回的支付时间转换0000-00-00 00:00:00
     *
     * @param time 20150529121245
     * @return
     */
    public static String wxstrToDate(String time) {
        String n, y, d, s, f, m;
        n = time.substring(0, 4);
        y = time.substring(4, 6);
        d = time.substring(6, 8);
        s = time.substring(8, 10);
        f = time.substring(10, 12);
        m = time.substring(12, 14);
        return n + "-" + y + "-" + d + " " + s + ":" + f + ":" + m;
    }

}
