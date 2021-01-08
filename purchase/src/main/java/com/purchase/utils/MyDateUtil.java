package com.purchase.utils;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Gray on 2018/9/15
 */
public class MyDateUtil {
    private static Logger logs = LogManager.getLogger(MyDateUtil.class);

    public static String simpleDateString(Date date,String simple){
        SimpleDateFormat format = new SimpleDateFormat(simple);
        String dateStr = format.format(date);
        return dateStr;
    }
    public static Date getPatternToDate(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        return format.parse(dateStr);
    }

    public static Date getTodayDate() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format1.format(new Date());
        return format2.parse(dateStr+" 00:00:00");
    }

    public static Date getPatternToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

    public static String getPatternToString(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String getPattern(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date getCurrentDate() throws ParseException {
        return getPatternToDate(new Date());
    }

    /**
     * 取oldDate的时分秒，替换给newDate
     * @param oldDate
     * @param newDate
     * @return
     */
    public static Date replaceDate(Date oldDate, Date newDate) {
        Calendar oldCal = Calendar.getInstance();
        Calendar newCal = Calendar.getInstance();
        oldCal.setTime(oldDate);
        newCal.setTime(newDate);
        oldCal.set(newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH), newCal.get(Calendar.DATE));
        //newCal.set(oldCal.get(Calendar.HOUR), oldCal.get(Calendar.MINUTE), oldCal.get(Calendar.SECOND));
        return oldCal.getTime();
    }

    public static int computeDay(Date oldDate, Date newDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(oldDate);   //设置为另一个时间
        c2.setTime(newDate);   //设置为另一个时间

        int oldDay = c1.get(Calendar.DAY_OF_MONTH);
        int newDay = c2.get(Calendar.DAY_OF_MONTH);
        return newDay - oldDay;
    }

    /**
     * 根据开始时间和结束时间返回集合，间隔为1天
     */
    public static List<String> getDateList(Date startTiem, Date endTime) throws ParseException {
        List<String> list= new ArrayList<>();
        while (startTiem.getTime() < endTime.getTime()){
            list.add(getPattern(startTiem));
            startTiem = DateUtils.addDays(startTiem,1);
        }
        return list;
    }

    public static String getWeek() {
        String week = "";
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = "7";
        } else if (weekday == 2) {
            week = "1";
        } else if (weekday == 3) {
            week = "2";
        } else if (weekday == 4) {
            week = "3";
        } else if (weekday == 5) {
            week = "4";
        } else if (weekday == 6) {
            week = "5";
        } else if (weekday == 7) {
            week = "6";
        }
        return week;
    }

    public static void main(String[] args) {
        System.out.println(getWeek());
    }
}
