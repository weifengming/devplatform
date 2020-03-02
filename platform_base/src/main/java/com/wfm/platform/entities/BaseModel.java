package com.wfm.platform.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Weifengming
 * @description 基础实体类
 * @date 2020/2/17
 */
@ApiModel(description = "基础实体类")
@Data
public abstract class BaseModel<PK extends Serializable> {
    private static final long serialVersionUID = 3796984803158565007L;

    @ApiModelProperty(name = "createBy", value = "创建人ID")
    protected String createBy;

    @ApiModelProperty(name = "updateBy", value = "更新人ID")
    protected String updateBy;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    protected LocalDateTime createTime;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    protected LocalDateTime updateTime;

    public abstract void setId(PK paramPK);

    public abstract PK getId();
}
