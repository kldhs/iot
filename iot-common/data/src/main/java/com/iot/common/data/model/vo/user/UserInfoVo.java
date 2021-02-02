package com.iot.common.data.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(name = "UserInfoVo", title = "用户基本信息")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {

    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "手机号")
    private String phone;


    @Schema(title = "企业id")
    private Long companyId;

    @Schema(title = "企业编号")
    private String companyCode;

    @Schema(title = "企业name")
    private String companyName;

    @Schema(title = "部门名称")
    private String departmentName;

    @Schema(title = "职位名称")
    private String positionName;

    @Schema(title = "姓名")
    private String name;
}
