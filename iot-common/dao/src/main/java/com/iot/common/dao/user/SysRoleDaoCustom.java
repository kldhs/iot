package com.iot.common.dao.user;


import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.dto.user.RoleSearchDto;
import com.iot.common.data.model.vo.user.RoleItemVo;

import java.util.List;

public interface SysRoleDaoCustom {

    /**
     * 分页查询
     */
    PageData<RoleItemVo> findPageByLevelAndCompanyAndRoleName(Boolean admin, RoleSearchDto roleSearchDto);


    /**
     * find level two ids
     */
    List<Long> findIdsForLevelTwo(String companyCode,List<Long> excludeIds);
}
