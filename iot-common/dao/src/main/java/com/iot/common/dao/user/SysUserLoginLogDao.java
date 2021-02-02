package com.iot.common.dao.user;


import com.iot.common.entity.user.UserLoginLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SysUserLoginLogDao extends JpaRepository<UserLoginLogEntity, Long>, QuerydslPredicateExecutor<UserLoginLogEntity> {

    UserLoginLogEntity findByUserId(Long userId);
}
