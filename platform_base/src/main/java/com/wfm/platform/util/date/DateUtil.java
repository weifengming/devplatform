package com.wfm.platform.util.date;

import com.wfm.platform.util.StringHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Weifengming
 * @description TODO
 * @date 2020/2/21
 */
public class DateUtil {
    private static final Log logger = LogFactory.getLog(DateUtil.class);

    public static Calendar toCalendar(LocalDateTime date) {
        Calendar c = Calendar.getInstance();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = date.atZone(zone).toInstant();
        Date udate = Date.from(instant);
        c.setTime(udate);
        return c;
    }

    public static LocalDateTime setAsBegin(LocalDateTime date) {
        LocalDateTime ndate = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0, 0);
        return ndate;
    }

    public static LocalDateTime setAsEnd(LocalDateTime date) {
        LocalDateTime ndate = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 23, 59, 59);
        return ndate;
    }

    public static String getCurrentTime(String style) {
        if (StringHelper.isEmpty(style))
            style = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(style);
        return LocalDateTime.now().format(formatter);
    }

    public static String getCurrentTime() {
        return getCurrentTime("");
    }

    public static LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }

    public static long getCurrentTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static LocalDateTime[] getDaysBetween(LocalDateTime startDate, LocalDateTime endDate) {
        long day = (startDate.toInstant(ZoneOffset.of("+8")).toEpochMilli() - endDate.toInstant(ZoneOffset.of("+8")).toEpochMilli()) / 86400000L > 0L ?
                (startDate
                        .toInstant(ZoneOffset.of("+8"))
                        .toEpochMilli() - endDate
                        .toInstant(ZoneOffset.of("+8"))
                        .toEpochMilli()) / 86400000L :
                (endDate
                        .toInstant(ZoneOffset.of("+8"))
                        .toEpochMilli() - startDate.toInstant(ZoneOffset.of("+8")).toEpochMilli()) / 86400000L;

        LocalDateTime[] dateArr = new LocalDateTime[Integer.valueOf(String.valueOf(day + 1L)).intValue()];

        for (int i = 0; i < dateArr.length; i++) {
            if (i == 0) {
                dateArr[i] = setAsBegin(startDate);
            } else {
                LocalDateTime nextDay = startDate.plusDays(1L);
                startDate = setAsBegin(nextDay);
                dateArr[i] = startDate;
            }
        }
        return dateArr;
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        return cal.getActualMaximum(5);
    }

    public static int getWeekDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        return cal.get(7);
    }

    public static boolean compare(String beginDateStr, String endDateStr) {
        try {
            LocalDateTime beginDate = DateFormatUtil.parse(beginDateStr);
            LocalDateTime endDate = DateFormatUtil.parse(endDateStr);
            return beginDate.compareTo(endDate) < 0;
        } catch (Exception e) {
        }
        return false;
    }

    public static int compareTo(String beginDateStr, String endDateStr) {
        try {
            LocalDateTime beginDate = DateFormatUtil.parse(beginDateStr);
            LocalDateTime endDate = DateFormatUtil.parse(endDateStr);
            return beginDate.compareTo(endDate);
        } catch (Exception e) {
        }
        return -2;
    }

    public static LocalDateTime getDate(int year, int month, int date) {
        return getDate(year, month, date, 0, 0, 0);
    }

    public static LocalDateTime getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        LocalDateTime dateTime = LocalDateTime.of(year, month, date, hourOfDay, minute, second);
        return dateTime;
    }

    public static long getTime(LocalDateTime startTime, LocalDateTime endTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant sinstant = startTime.atZone(zone).toInstant();
        Instant einstant = endTime.atZone(zone).toInstant();
        return einstant.toEpochMilli() - sinstant.toEpochMilli();
    }

    public static String getDurationTime(LocalDateTime date) {
        return getDurationTime(date, LocalDateTime.now());
    }

    public static String getDurationTime(LocalDateTime startTime, LocalDateTime endTime) {
        if ((startTime == null) || (endTime == null))
            return "";
        Long millseconds = Long.valueOf(getTime(startTime, LocalDateTime.now()));
        return getTime(millseconds);
    }

    public static String getTime(Long millseconds) {
        StringBuffer time = new StringBuffer();
        if (millseconds == null)
            return "";
        int days = (int) millseconds.longValue() / 1000 / 60 / 60 / 24;
        if (days > 0)
            time.append(days).append("天");
        long hourMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24;
        int hours = (int) hourMillseconds / 1000 / 60 / 60;
        if (hours > 0)
            time.append(hours).append("小时");
        long minuteMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24 - hours * 1000 * 60 * 60;

        int minutes = (int) minuteMillseconds / 1000 / 60;
        if (minutes > 0)
            time.append(minutes).append("分钟");
        return time.toString();
    }

    public static boolean belongCalendar(LocalDateTime nowTime, LocalDateTime beginTime, LocalDateTime endTime) {
        Calendar date = Calendar.getInstance();
        ZoneId zone = ZoneId.systemDefault();
        Instant nowinstant = nowTime.atZone(zone).toInstant();
        Date nowdate = Date.from(nowinstant);
        date.setTime(nowdate);

        Instant begininstant = beginTime.atZone(zone).toInstant();
        Date begindate = Date.from(begininstant);
        Calendar begin = Calendar.getInstance();
        begin.setTime(begindate);

        Instant endinstant = endTime.atZone(zone).toInstant();
        Date enddate = Date.from(endinstant);
        Calendar end = Calendar.getInstance();
        end.setTime(enddate);

        return ((date.after(begin)) || (nowinstant.toEpochMilli() == begininstant.toEpochMilli())) && (
                (date
                        .before(end)) ||
                        (nowinstant.toEpochMilli() == endinstant.toEpochMilli()));
    }
}
