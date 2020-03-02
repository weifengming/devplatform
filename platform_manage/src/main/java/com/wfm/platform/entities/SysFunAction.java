package com.wfm.platform.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Weifengming
 * @description 功能项
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "功能项")
public class SysFunAction extends BaseModel<String> {

    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "funId", value = "功能ID")
    private String funId;

    @ApiModelProperty(name = "funactionName", value = "功能项名称")
    private String funactionName;

    @ApiModelProperty(name = "path", value = "功能项路径")
    private String path;
    
    @ApiModelProperty(name = "comment", value = "功能描述")
    private String comment;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
