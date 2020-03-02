package com.wfm.platform.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@ApiModel
public class Result<E> {
    //是否成功
    @ApiModelProperty(name = "state", value = "true:操作成功 false:操作失败")
    private boolean state;
    //返回码
    @ApiModelProperty(name = "code", value = "结果返回状态码")
    private Integer code;
    //返回信息
    @ApiModelProperty(name = "message", value = "结果返回提示信息")
    private String message;
    //返回数据
    @ApiModelProperty(name = "data", value = "结果返回数据")
    private E data;

    public Result(boolean state, Integer code, String message) {
        this.state = state;
        this.code = code;
        this.message = message;
    }
}
