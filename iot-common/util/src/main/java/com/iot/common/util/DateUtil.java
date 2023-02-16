package com.iot.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * LocalDateTime：本地日期时间
 * OffsetDateTime：带偏移量的日期时间
 * ZonedDateTime：带时区的日期时间
 */
public class DateUtil {
    private DateUtil() {
    }

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final DateTimeFormatter OFFSET_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
    public static final DateTimeFormatter OFFSET_DATE_TIME_FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmZZZZZ");
    public static final DateTimeFormatter OFFSET_DATE_TIME_FORMATTER3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZZZZZ");

    /**
     * 获取当前LocalDateTime字符串
     * @return
     */
    public static String getNowLocalDateTimeString() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    /**
     * Date转LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        var instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime转Date
     * @param time
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime time) {
        if (time == null) return null;
        var instant = time.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 毫秒转LocalDateTime
     * @param ts
     * @return
     */
    public static LocalDateTime milliToLocalDateTime(Long ts) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(ts), ZoneId.systemDefault());
    }

    /**
     * 毫秒转OffsetDateTime
     * @param ts
     * @return
     */
    public static OffsetDateTime milliToOffsetDateTime(Long ts) {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(ts), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转毫秒
     * @param localDateTime
     * @return
     */
    public static Long localDateTimeToMilli(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime转OffsetDateTime
     * @param localDateTime
     * @return
     */
    public static OffsetDateTime localToOffsetDateTime(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) return null;
        return OffsetDateTime.ofInstant(localDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    }

    /**
     * OffsetDateTime转
     * @param offsetDateTime
     * @return
     */
    public static LocalDateTime offsetToLocalDateTime(OffsetDateTime offsetDateTime) {
        if (Objects.isNull(offsetDateTime)) return null;
        return offsetDateTime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * OffsetDateTime格式的字符串转OffsetDateTime
     * @param time
     * @return
     */
    public static OffsetDateTime stringToOffsetDateTime(String time) {
        try {
            return OffsetDateTime.parse(time, OFFSET_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException ignored) {
            //ignore
        }
        try {
            return OffsetDateTime.parse(time, OFFSET_DATE_TIME_FORMATTER2);
        } catch (DateTimeParseException ignored) {
            //ignore
        }
        throw new IllegalArgumentException("time format parse error");
    }

    /**
     * LocalDateTime格式的字符串转LocalDateTime
     * @param timeString
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String timeString) {
        try {
            return LocalDateTime.parse(timeString, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException ignored) {
            //ignore
        }
        try {
            return LocalDateTime.parse(timeString, FORMATTER2);
        } catch (DateTimeParseException ignored) {
            //ignore
        }
        throw new IllegalArgumentException("time format error");
    }

    /**
     * 计算两个LocalDateTime之间相差的月份
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int calculateMonthsBetweenLocalDateTime(LocalDateTime fromDate, LocalDateTime toDate) {
        return (int) ChronoUnit.MONTHS.between(fromDate, toDate);
    }


}
