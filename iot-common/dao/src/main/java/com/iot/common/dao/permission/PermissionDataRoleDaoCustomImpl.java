package com.iot.common.dao.permission;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.iot.common.data.model.dto.permission.PermissionPartSearchDto;
import com.iot.common.data.model.dto.permission.PermissionProductSearchDto;
import com.iot.common.data.model.vo.PartPermissionInfoVo;
import com.iot.common.data.model.vo.product.ProductPermissionInfoVo;
import com.iot.common.entity.chip.QChipManufacturerEntity;
import com.iot.common.entity.chip.QChipModelEntity;
import com.iot.common.entity.chip.QChipSeriesEntity;
import com.iot.common.entity.company.QCompanyBrandEntity;
import com.iot.common.entity.company.QCompanyChipModelEntity;
import com.iot.common.entity.company.QCompanySupplierEntity;
import com.iot.common.entity.permission.QPermissionDataRoleEntity;
import com.iot.common.entity.product.QProductInfoEntity;
import com.querydsl.core.types.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;


public class PermissionDataRoleDaoCustomImpl implements PermissionDataRoleDaoCustom{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CriteriaBuilderFactory builderFactory;

}
