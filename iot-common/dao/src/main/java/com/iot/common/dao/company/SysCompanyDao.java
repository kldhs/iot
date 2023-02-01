package com.iot.common.dao.company;


import com.iot.common.dao.base.BaseDao;
import com.iot.common.entity.company.CompanyEntity;
import com.iot.common.data.model.vo.company.CompanyItemVo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SysCompanyDao extends BaseDao<CompanyEntity, Long>,SysCompanyDaoCustom{

    CompanyEntity findByCompanyCodeAndDeletedIsFalse(String companyCode);

    CompanyEntity findByIdAndDeletedIsFalse(Long id);

    Boolean existsByCompanyCodeAndDeletedIsFalse(String companyCode);

    Boolean existsByCompanyNameAndDeletedIsFalse(String companyName);

    @Query("SELECT NEW com.abupdate.ucc.common.data.model.vo.company.CompanyItemVo(e.id,e.companyCode,e.companyName)" +
            " FROM CompanyEntity e where e.status = 1 and e.deleted=false")
    List<CompanyItemVo> findAllSelect();

    Boolean existsByIdAndDeletedFalse(Long companyId);
}
