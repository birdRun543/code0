package com.birdRun543.code0.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 日期处理工具
 *
 * @author hanbing
 * @date 2019-6-5
 */

@Slf4j
public class DateHandleUtil {

    public static Date preDay(Date currentDay) {
        return addDay(currentDay, -1);
    }

    public static Date nextDay(Date currentDay) {
        return addDay(currentDay, +1);
    }

    public static Date addDay(Date currentDay, int daySize) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDay);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + daySize);
        return calendar.getTime();
    }

    public static Date addSecoend(Date currentDay, int secSize) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDay);
        calendar.add(Calendar.SECOND, secSize);
        return calendar.getTime();
    }

    /**
     * ChronoUnit的between方法签名为，between(Temporal temporal1Inclusive, Temporal temporal2Exclusive)，
     * 需要注意的是，如果要以不同的单位展示时间差，between入参中的时间对象必须包含有对应的时间信息，
     * 否则会抛出java.time.temporal.UnsupportedTemporalTypeException: Unsupported unit XXX的异常
     */
    public static int dayDiff(Date day1, Date day2) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day1);

        LocalDate startDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));


        calendar.setTime(day2);
        LocalDate endDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));

        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);

        //  long hoursDiff = ChronoUnit.HOURS.between(startDate, endDate);  //这句会抛出异常，因为LocalDate表示的时间中不包含时分秒等信息

        //        logger.debug("开始时间：" + startDate);
        //        logger.debug("结束时间：" + endDate);
        //        logger.debug("两个时间之间的天数差为：" + daysDiff);

        return Math.toIntExact(daysDiff);
    }

    public static int hourDiff(Date day1, Date day2) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;

        long hourDiff = (day1.getTime() - day2.getTime()) % nd / nh;
        log.debug("===" + String.valueOf(hourDiff));

        return Math.toIntExact(hourDiff);
    }


    public static int getDueDay(Date dueDate) {
        Date d1 = addDay(dueDate, -280);
        return dayDiff(d1, new Date());
    }

    public static Date str2Date(String dayStr) {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//
//        try {
//            return sdf.parse(dayStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return null;

        return str2Date(dayStr, "yyyy-MM-dd");
    }

    public static Date str2Date(String dayStr, String formatStr) {

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            return sdf.parse(dayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

    }

    public static Date getTodayZero() throws ParseException {

        return getDayZero(new Date());
    }

    public static Date getDayZero(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

        return reformatDate(sdf, date);
    }

    public static Date getHourZero(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        return reformatDate(sdf, date);
    }

    public static String getWeekOfDay(Date date) {

        List<String> week = Arrays.asList("周日", "周一", "周二", "周三", "周四", "周五", "周六");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return week.get(cal.get(Calendar.DAY_OF_WEEK) - 1);

    }

    public static Date setYearRange(Integer year, Integer kind) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();
        if (kind == 0) {
            cal.set(year, 0, 1);
        } else {
            cal.set(year, 11, 1);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return reformatDate(sdf, cal.getTime());
    }

    public static Date reformatDate(SimpleDateFormat sdf, Date day) throws ParseException {
        return sdf.parse(sdf.format(day));
    }

    public static String format(Date date, String formatStr) {
        SimpleDateFormat sdf0 = new SimpleDateFormat(formatStr);
        return sdf0.format(date);
    }

    public static Integer checkExpire(Date endDate, Date date) {
        return endDate.compareTo(date) > 0 ? 0 : 1;
    }

    public static int getTimeDeltaSecond(Date date1, Date date2) {

        try {
            //单位是秒
            return (int) Math.abs((date1.getTime() - date2.getTime()) / 1000);
        } catch (Exception ex) {
            // 异常情况返回最大值
            return 0;
        }
    }

    public static Date getLastWeekDay1() throws ParseException {
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 1);

        return cal.getTime();
    }

    public static Date getLastMonthDay1() throws ParseException {
        return getMonthDay1(new Date());
    }

    public static Date getMonthDay1(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        return reformatDate(sdf, date);
    }

    public static Date getLastYearDay1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-01-01 00:00:00");
        return reformatDate(sdf, new Date());
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static Date getNextMonthDay(Date startMonthDay, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startMonthDay);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }
}
