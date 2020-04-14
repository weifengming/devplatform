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
 * @description 用户
 * @date 2020/2/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "系统用户")
public class SysUser extends BaseModel<String> implements Serializable {
    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @ApiModelProperty(name = "username", value = "登录名")
    private String username;

    @ApiModelProperty(name = "realname", value = "真实姓名")
    private String realname;

    @ApiModelProperty(name = "password", value = "密码")
    private String password;

    @ApiModelProperty(name = "sex", value = "性别")
    private String sex;

    @ApiModelProperty(name = "salt", value = "盐值")
    private String salt;

    @ApiModelProperty(name = "headImg", value = "用户头像")
    private String headImg;

    @ApiModelProperty(name = "telphone", value = "手机号")
    private String telphone;

    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;

    @ApiModelProperty(name = "orgId", value = "机构ID")
    private String orgId;

    @ApiModelProperty(name = "status", value = "状态")
    private Integer status;

    @ApiModelProperty(name = "comment", value = "描述")
    private String comment;

    @ApiModelProperty(name = "delFlag", value = "删除标识")
    private Integer delFlag;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
