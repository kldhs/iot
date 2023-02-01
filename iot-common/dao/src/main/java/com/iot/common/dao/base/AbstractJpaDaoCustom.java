package com.iot.common.dao.base;

import com.blazebit.persistence.CriteriaBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public abstract class AbstractJpaDaoCustom {

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected CriteriaBuilderFactory builderFactory;
}
