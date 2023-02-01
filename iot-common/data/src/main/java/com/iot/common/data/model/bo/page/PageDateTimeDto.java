package com.iot.common.data.model.bo.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.common.data.constant.DateConstant;
import com.iot.common.data.enums.ResultEnum;
import com.iot.common.data.exception.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Data
public class PageDateTimeDto {

    @NotNull(message = "搜索开始时间不能为空")
    private String startTime;

    @NotNull(message = "搜索结束时间不能为空")
    private String endTime;

    @Min(value = 0, message = "页码最小值为0")
    @NotNull(message = "页码不能为空")
    private Integer pageNumber;
    @Min(value = 1, message = "分页最小值为1")
    @Max(value = 100, message = "分页最大值为100")
    @NotNull(message = "分页不能为空")
    private Integer pageSize;

    @JsonIgnore
    public long getStartTimeTs() {
        return getOffsetDateLocalTs(startTime, true);
    }

    @JsonIgnore
    public long getEndTimeTs() {
        return getOffsetDateLocalTs(endTime, false);
    }

    @JsonIgnore
    public long getTimestamp(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    //yyyy-MM-dd
    @JsonIgnore
    public long getOffsetDateLocalTs(String time, boolean isStart){
        if (!StringUtils.hasText(time)) {
            throw new BusinessException(ResultEnum.DATETIME_VALUE_ERROR);
        }
        try {
            var temporalAccessor = DateTimeFormatter.ISO_OFFSET_DATE.parse(time);
            var offsetDateTime = OffsetDateTime.of(LocalDate.from(temporalAccessor),isStart ? LocalTime.MIN:LocalTime.MAX,ZoneOffset.from(temporalAccessor));
            log.info("time={}, convert day start={} offsetDateTime={}", time, isStart, offsetDateTime);
            return offsetDateTime.atZoneSameInstant(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }catch (DateTimeParseException ignored){
            //ignore
        }
        try {
            var date = LocalDate.parse(time, DateConstant.DATE_FORMAT);
            return getTimestamp(LocalDateTime.of(date, isStart ? LocalTime.MIN:LocalTime.MAX));
        }catch (DateTimeParseException ignored){
            //ignore
        }
        throw new BusinessException(ResultEnum.DATETIME_VALUE_ERROR);
    }
}
