package com.wfm.platform.entities;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Weifengming
 * @description 字典
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "数据字典")
public class SysDict extends BaseModel<String> {
    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "parentId", value = "父级ID")
    private String parentId;

    @ApiModelProperty(name = "code", value = "字典编码")
    private String code;

    @ApiModelProperty(name = "dictName", value = "字典名称")
    private String dictName;

    @ApiModelProperty(name = "dictValue", value = "字典值")
    private String dictValue;

    @ApiModelProperty(name = "comment", value = "字典备注")
    private String comment;
    
    @ApiModelProperty(name = "orderNum", value = "排序")
    private Integer orderNum;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
