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
 *     LocalDateTime：本地日期时间
 *     OffsetDateTime：带偏移量的日期时间
 *     ZonedDateTime：带时区的日期时间
 */
public class DateUtil {
    private DateUtil() {
    }

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final DateTimeFormatter OFFSET_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
    private static final DateTimeFormatter OFFSET_DATE_TIME_FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmZZZZZ");
    private static final DateTimeFormatter OFFSET_DATE_TIME_FORMATTER3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZZZZZ");

    public static String getNowStr() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime dateToJava8Type(Date date) {
        if (date == null) {
            return null;
        }
        var instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date java8TypeToDate(LocalDateTime time) {
        if (time == null) return null;
        var instant = time.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDateTime tsMilliToLocalDateTime(Long ts) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(ts), ZoneId.systemDefault());
    }

    public static OffsetDateTime tsMilliToOffsetDateTime(Long ts) {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(ts), ZoneId.systemDefault());
    }

    public static OffsetDateTime localToOffsetDateTime(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) return null;
        return OffsetDateTime.ofInstant(localDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime offsetToLocalDateTime(OffsetDateTime offsetDateTime) {
        if (Objects.isNull(offsetDateTime)) return null;
        return offsetDateTime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static OffsetDateTime parseOffsetTime(String time) {
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

    public static Long stringToTimestamp(String timeString) {
        try {
            return LocalDateTime.parse(timeString, DATE_TIME_FORMATTER).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
        } catch (DateTimeParseException ignored) {
            //ignore
        }
        try {
            return LocalDateTime.parse(timeString, FORMATTER2).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
        } catch (DateTimeParseException ignored) {
            //ignore
        }
        throw new IllegalArgumentException("time format error");
    }

    public static int calculateMonthsBetweenTwoStr(String fromDate, String toDate) {
        var fromLocalDateTime = LocalDateTime.parse(fromDate, DATE_TIME_FORMATTER);
        var toLocalDateTime = LocalDateTime.parse(toDate, DATE_TIME_FORMATTER);
        return (int) ChronoUnit.MONTHS.between(fromLocalDateTime, toLocalDateTime);
    }

    public static Long localToMillisecond(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
