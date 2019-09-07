package com.ckong.schedule.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * 时间与日期工具类
 * @author kongzhiqiang
 */
public class DateTimeUtil {

    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd:hh:mm:ss";
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_TIME_PATTERN = "hh:mm:ss";

    /**
     * 日期时间转String
     * @param dateTime 时间对象可以LocalDateTime、LocalDate、LocalTime not null
     * @param pattern 转换的pattern可以为空
     * @return 字符串类型的时间
     */
    public static  String timeToString(final LocalDateTime dateTime, final String ...pattern){

        Objects.requireNonNull(dateTime, "DateTime为空");

        return Objects.nonNull(pattern)  && pattern.length > 0?
                dateTime.format(DateTimeFormatter.ofPattern(pattern[0])) :
                dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN));
    }

    public static String timeToString(final LocalTime dateTime, final String ...pattern) {

        Objects.requireNonNull(dateTime, "Time为空");

        return Objects.nonNull(pattern)  && pattern.length > 0?
                dateTime.format(DateTimeFormatter.ofPattern(pattern[0])) :
                dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN));
    }

    public static String timeToString(final LocalDate date, final String ...pattern) {

        Objects.requireNonNull(date, "Date为空");

        return Objects.nonNull(pattern)  && pattern.length > 0?
                date.format(DateTimeFormatter.ofPattern(pattern[0])) :
                date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));
    }

    public static String timeToString(final Date date, final String ...pattern) {

        Objects.requireNonNull(date, "Date为空");

        LocalDateTime localDateTime = dateToLocalLocalDateTime(date);
        return Objects.nonNull(pattern)  && pattern.length > 0 ?
                localDateTime.format(DateTimeFormatter.ofPattern(pattern[0])) :
                localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));

    }
    public static String timToString(final Timestamp timestamp, final String ...pattern) {

        Objects.requireNonNull(timestamp);
        LocalDateTime time = timeStampToLocalDateTime(timestamp);

        return Objects.nonNull(pattern)  && pattern.length > 0 ?
                time.format(DateTimeFormatter.ofPattern(pattern[0])) :
                time.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));

    }

    /**
     * 比较时间日期
     * @param first 第一个参数
     * @param second 第二个参数
     * @return 相等 0 first>second 1, first<second 小于0的数
     */
    public static int compare(final LocalDateTime first,final LocalDateTime second) {

        Objects.requireNonNull(first, "firstDate为空");
        Objects.requireNonNull(second, "secondDate为空");
        return first.compareTo(second);

    }
    public static int compare(final LocalDate first, final LocalDate second) {

        Objects.requireNonNull(first, "firstDate为空");
        Objects.requireNonNull(second, "secondDate为空");
        return first.compareTo(second);
    }
    public static int compare(final LocalTime first, final LocalTime second) {

        Objects.requireNonNull(first, "firstTime为空");
        Objects.requireNonNull(second, "secondTime为空");
        return first.compareTo(second);
    }
    public static int compare(final Date first, final LocalDateTime local) {

        Objects.requireNonNull(first, "firstDate为空");
        Objects.requireNonNull(local, "secondDateTime为空");

        return dateToLocalLocalDateTime(first).compareTo(local);
    }

    public static int compare(final LocalDateTime first, final Date second) {
        Objects.requireNonNull(first, "firstDateTime为空");
        Objects.requireNonNull(second, "Date Second为空");

        return first.compareTo(dateToLocalLocalDateTime(second));
    }


    /**
     * date对象转LocalDateTime
     * @param date Date类型对象 not null
     * @return 经过转换后的对象
     */
    public static LocalDateTime dateToLocalLocalDateTime(final Date date) {

        Objects.requireNonNull(date, "Date为空");

        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 将TimeStamp转换成LocalDateTime
     * @param timestamp 时间戳
     * @return 转换后的结果
     */
    public static LocalDateTime timeStampToLocalDateTime(final Timestamp timestamp) {

        Objects.requireNonNull(timestamp, "TimStamp为空");

        Instant instant = timestamp.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }


}
