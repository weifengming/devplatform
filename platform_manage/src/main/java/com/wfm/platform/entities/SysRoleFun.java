package com.wfm.platform.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Weifengming
 * @description 角色功能
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "角色功能关系")
public class SysRoleFun implements Serializable {

    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "funId", value = "功能ID")
    private String funId;

    @ApiModelProperty(name = "roleId", value = "角色ID")
    private String roleId;

}
