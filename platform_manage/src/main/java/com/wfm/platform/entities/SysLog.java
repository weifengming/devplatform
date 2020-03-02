package com.wfm.platform.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Weifengming
 * @description 日志
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "日志")
public class SysLog implements Serializable {

    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "id", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "id", value = "用户ID")
    private String userId;

    @ApiModelProperty(name = "id", value = "请求方法")
    private String method;

    @ApiModelProperty(name = "id", value = "请求参数")
    private String params;

    @ApiModelProperty(name = "id", value = "请求耗时")
    private Integer time;

    @ApiModelProperty(name = "id", value = "请求IP")
    private String ip;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private LocalDateTime createTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
