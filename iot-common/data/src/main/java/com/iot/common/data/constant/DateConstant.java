package com.iot.common.data.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConstant {
    private DateConstant(){

    }

    public static final String DATETIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_PATTERN_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String DATE_ONLY_FORMAT = "yyMMdd";
    public static final String TIME_MINUTE_FORMAT = "HH:mm";

    public static final String OFFSET_DATETIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
    public static final String OFFSET_DATETIME_FORMAT_PATTERN2 = "yyyy-MM-dd HH:mm:ssZZZZZ";
    public static final String OFFSET_DATETIME_FORMAT_PATTERN_MINUTE = "yyyy-MM-dd'T'HH:mmZZZZZ";
    public static final String OFFSET_DATETIME_FORMAT_PATTERN_MINUTE2 = "yyyy-MM-dd HH:mmZZZZZ";

    public static final DateTimeFormatter DATETIME_FORMAT_SEC = DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN);
    public static final DateTimeFormatter DATETIME_FORMAT_MIN = DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN_MINUTE);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_MINUTE_FORMAT);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    public static final DateTimeFormatter OFFSET_DATETIME_FORMAT_SEC = DateTimeFormatter.ofPattern(OFFSET_DATETIME_FORMAT_PATTERN);
    public static final DateTimeFormatter OFFSET_DATETIME_FORMAT_SEC2 = DateTimeFormatter.ofPattern(OFFSET_DATETIME_FORMAT_PATTERN2);
    public static final DateTimeFormatter OFFSET_DATETIME_FORMAT_MIN = DateTimeFormatter.ofPattern(OFFSET_DATETIME_FORMAT_PATTERN_MINUTE);
    public static final DateTimeFormatter OFFSET_DATETIME_FORMAT_MIN2 = DateTimeFormatter.ofPattern(OFFSET_DATETIME_FORMAT_PATTERN_MINUTE2);

    public static final LocalDateTime VALID_TIME = LocalDateTime.parse("2999-01-01T00:00:00");
}
