package com.iot.common.dao.base;

import com.blazebit.persistence.CriteriaBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

public abstract class AbstractDaoCustom {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected TransactionTemplate transactionTemplate;

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected CriteriaBuilderFactory builderFactory;
}
