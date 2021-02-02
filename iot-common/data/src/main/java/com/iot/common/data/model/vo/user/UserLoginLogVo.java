package com.iot.common.data.model.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iot.common.data.constant.DateConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginLogVo{

        private Long userId;
        @JsonFormat(pattern = DateConstant.DATETIME_FORMAT_PATTERN)
        private LocalDateTime loginTime;
}
