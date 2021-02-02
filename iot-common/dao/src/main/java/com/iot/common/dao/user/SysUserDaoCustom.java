package com.iot.common.dao.user;


import com.iot.common.data.model.bo.page.PageData;
import com.iot.common.data.model.dto.user.UserSearchDto;
import com.iot.common.data.model.vo.user.UserItemVo;

public interface SysUserDaoCustom {

    /**
     * 分页查询 管理端
     */
    PageData<UserItemVo> findUserListPage(UserSearchDto userSearchDto);


    /**
     * 分页查询 客户端
     */
    PageData<UserItemVo> findUserListPage(String companyCode,UserSearchDto userSearchDto);


}
