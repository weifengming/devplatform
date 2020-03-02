package com.wfm.platform.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Weifengming
 * @description 组织机构
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "系统用户角色")
public class SysRole extends BaseModel<String> {
    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "roleName", value = "角色名称")
    private String roleName;

    @ApiModelProperty(name = "comment", value = "角色描述")
    private String comment;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
