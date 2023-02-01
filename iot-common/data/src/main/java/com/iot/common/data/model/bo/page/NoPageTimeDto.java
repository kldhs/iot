package com.iot.common.data.model.bo.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.common.data.constant.DateConstant;
import com.iot.common.data.constant.DeviceConstant;
import com.iot.common.data.enums.ResultEnum;
import com.iot.common.data.exception.BusinessException;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

@Data
public class NoPageTimeDto {

    private String startTime;

    private String endTime;

    @JsonIgnore
    private Integer limitSize = DeviceConstant.PAGE_SIZE_LIMIT;

    @JsonIgnore
    public Long getStartTimeTs() {
        return stringToTimestamp(this.startTime);
    }

    @JsonIgnore
    public Long getEndTimeTs() {
        return stringToTimestamp(this.endTime);
    }

    private Long stringToTimestamp(String time) {
        if (!StringUtils.hasText(time)) return null;
        try{
            return OffsetDateTime.parse(time, DateConstant.OFFSET_DATETIME_FORMAT_SEC).toInstant().toEpochMilli();
        }catch (DateTimeParseException ignored){
            //ignore
        }
        try{
            return OffsetDateTime.parse(time, DateConstant.OFFSET_DATETIME_FORMAT_SEC2).toInstant().toEpochMilli();
        }catch (DateTimeParseException ignored){
            //ignore
        }
        try{
            return OffsetDateTime.parse(time, DateConstant.OFFSET_DATETIME_FORMAT_MIN).toInstant().toEpochMilli();
        }catch (DateTimeParseException ignored){
            //ignore
        }
        try{
            return OffsetDateTime.parse(time, DateConstant.OFFSET_DATETIME_FORMAT_MIN2).toInstant().toEpochMilli();
        }catch (DateTimeParseException ignored){
            //ignore
        }
        try {
            return LocalDateTime.parse(time, DateConstant.DATETIME_FORMAT_SEC).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
        } catch (DateTimeParseException ignored) {
            //ignore
        }
        try {
            return LocalDateTime.parse(time, DateConstant.DATETIME_FORMAT_MIN).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
        } catch (DateTimeParseException ignored) {
            //ignore
        }
        throw new BusinessException(ResultEnum.DATETIME_VALUE_ERROR);
    }
}
