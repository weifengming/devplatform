package com.wfm.platform.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Weifengming
 * @description 功能
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "系统功能")
public class SysFun extends BaseModel<String> {

    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "parentId", value = "父级ID")
    private String parentId;

    @ApiModelProperty(name = "funName", value = "功能名称")
    private String funName;

    @ApiModelProperty(name = "code", value = "功能编码")
    private String code;

    @ApiModelProperty(name = "comment", value = "功能描述")
    private String comment;

    @ApiModelProperty(name = "orderNum", value = "功能描述")
    private Integer orderNum;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
