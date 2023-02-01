package com.iot.common.entity.user;

import com.iot.common.data.constant.ModelConstants;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author wzh
 * @date 2022/7/25 17:56
 * content:登录日志
 */
@ToString(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = ModelConstants.SYS_USER_LOGIN_LOG_TABLE,schema = ModelConstants.SCHEMA_PERMISSION)
public class UserLoginLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "int8",nullable = false)
    private Long userId;

    @Column(columnDefinition = "varchar(100)")
    private String loginIp;

    @CreationTimestamp
    private LocalDateTime lastLoginTime;


}
