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
 * @description 组织机构
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "组织机构")
public class SysOrg extends BaseModel<String> implements Serializable {

    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "parentId", value = "父级ID")
    private String parentId;

    @ApiModelProperty(name = "orgName", value = "机构名称")
    private String orgName;

    @ApiModelProperty(name = "code", value = "机构编码")
    private String code;

    @ApiModelProperty(name = "comment", value = "机构描述")
    private String comment;

    @ApiModelProperty(name = "orderNum", value = "序号")
    private Integer orderNum;

    @ApiModelProperty(name = "delFlag", value = "删除标识")
    private Integer delFlag;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
