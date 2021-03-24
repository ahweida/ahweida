package com.weida.epcommon.util;

import org.apache.commons.collections.list.TreeList;
import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DateUtil {

    /**
     * 日期格式 年 如2009
     */
    public static final String DATE_FORMAT_YEAR = "yyyy";

    /**
     * 日期格式 年 月  如 2009-02
     */
    public static final String DATE_FORMAT_MONTH = "yyyy-MM";

    /**
     * 日期格式 年 月 日 如2009-02-26
     */
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";

    /**
     * 日期格式 年 月 日 时 如2009-02-26 15
     */
    public static final String DATE_FORMAT_HOUR = "yyyy-MM-dd HH";

    /**
     * 日期格式 年 月 日 时 分 如2009-02-26 15:40
     */
    public static final String DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式年 月 日 时 分 秒 如 2009-02-26 15:40:00
     */
    public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式年 月 日 时 分 秒 毫秒 如2009-02-26 15:40:00 110
     */
    public static final String DATE_FORMAT_MILLI_SECOND = "yyyy-MM-dd HH:mm:ss SSS";


    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     *
     * @param string
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parase(String string, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(string);
    }


    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到毫秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }
    /**
     * 日期格式字符串转换成时间戳
     * @param date 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/2/24 14:59
     * @Desc: 日期格式字符串增加 n 秒
     */
    public static String timeStrAddTime(String timeStr, int value){
        String returnStr = "";
        try {
            Date date = DateUtil.parase(timeStr, DateUtil.DATE_FORMAT_SECOND);
            Date date1 = DateUtils.addSeconds(date, value);
            returnStr = DateUtil.format(date1, DateUtil.DATE_FORMAT_SECOND);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    public static String timeStrAddTime(String timeStr, int value, String format){
        String returnStr = "";
        try {
            Date date = DateUtil.parase(timeStr, format);
            Date date1 = DateUtils.addSeconds(date, value);
            returnStr = DateUtil.format(date1, format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    public static String timeAddTime(Date date, int value){
        Date date1 = DateUtils.addSeconds(date, value);
        return DateUtil.format(date1, DateUtil.DATE_FORMAT_SECOND);
    }



    public static void main(String[] args) {

        Set<String> datas = new TreeSet();
        datas.add("2021-02-25 00:00:00");
        datas.add("2021-02-25 01:00:00");
        datas.add("2021-02-25 02:00:00");
        datas.add("2021-02-25 00:00:01");
        datas.add("2021-02-25 00:00:01");

        for (String data: datas){
            System.out.println(data);
        }


//        String timeStamp = timeStamp();
//        System.out.println("timeStamp="+timeStamp); //运行输出:timeStamp=1470278082
//        System.out.println(System.currentTimeMillis());//运行输出:1470278082980
        //该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数

//        String date = timeStamp2Date("1609747440020", "yyyy-MM-dd HH:mm:ss");
//        System.out.println("date="+date);//运行输出:date=2016-08-04 10:34:42
//
//        String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");
//        System.out.println(timeStamp2);  //运行输出:1470278082


    }


}
