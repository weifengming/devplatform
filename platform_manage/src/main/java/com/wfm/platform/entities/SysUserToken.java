package com.wfm.platform.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Weifengming
 * @description 用户Token
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "用户token")
public class SysUserToken implements Serializable {

    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private String userId;

    @ApiModelProperty(name = "token", value = "token")
    private String token;

    @ApiModelProperty(name = "expireIn", value = "过期时间")
    private Date expireIn;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

}
