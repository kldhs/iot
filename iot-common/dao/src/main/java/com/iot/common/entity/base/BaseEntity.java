package com.iot.common.entity.base;


import com.iot.common.data.constant.ModelConstants;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(name = ModelConstants.CREATE_UNAME_PROPERTY, columnDefinition = "varchar(150) not null default ''")
    private String createUname = "";

    @LastModifiedBy
    @Column(name = ModelConstants.UPDATE_UNAME_PROPERTY, columnDefinition = "varchar(150) not null default ''")
    private String updateUname = "";

    @Column(name = ModelConstants.CREATE_TIME_PROPERTY)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name = ModelConstants.UPDATE_TIME_PROPERTY)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @Column(name = ModelConstants.DELETE_PROPERTY)
    private Boolean deleted = Boolean.FALSE;
}
