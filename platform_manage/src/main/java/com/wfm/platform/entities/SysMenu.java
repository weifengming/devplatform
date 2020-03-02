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
 * @description 菜单
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "系统菜单")
public class SysMenu extends BaseModel<String> {
    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "funCode", value = "功能编码")
    private String funCode;

    @ApiModelProperty(name = "parentId", value = "父级ID")
    private String parentId;

    @ApiModelProperty(name = "menuName", value = "菜单名称")
    private String menuName;

    @ApiModelProperty(name = "comment", value = "菜单描述")
    private String comment;

    @ApiModelProperty(name = "path", value = "请求地址")
    private String path;

    @ApiModelProperty(name = "icon", value = "菜单图标")
    private String icon;

    @ApiModelProperty(name = "orderNum", value = "序号")
    private Integer orderNum;
    
    @ApiModelProperty(name = "status", value = "状态")
    private Integer status;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
