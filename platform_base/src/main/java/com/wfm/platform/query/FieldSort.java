package com.wfm.platform.query;

import com.wfm.platform.util.FieldConvertUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * @author Weifengming
 * @description 排序对象
 * @date 2020/2/17
 */
@ApiModel(description = "排序对象")
public class FieldSort implements Serializable {
    private static final long serialVersionUID = -1712830705595375365L;

    private static String INJECTION_REGEX = "[A-Za-z0-9\\_\\-\\+\\.]+";

    @ApiModelProperty(name = "direction", value = "排序方向")
    private Direction direction;

    @ApiModelProperty(name = "property", value = "排序字段")
    private String property;

    public FieldSort() {
    }

    public FieldSort(String property) {
        this(property, Direction.ASC);
    }

    public FieldSort(String property, Direction direction) {
        this.direction = direction;
        this.property = property;
    }

    public static FieldSort create(String property, String direction) {
        return new FieldSort(property, Direction.fromString(direction));
    }

    public Direction getDirection() {
        return this.direction;
    }

    public String getProperty() {
        return this.property;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public static boolean isSQLInjection(String str) {
        return !Pattern.matches(INJECTION_REGEX, str);
    }

    public String toSql(Class<?> clazz) {
        if (isSQLInjection(this.property)) {
            throw new IllegalArgumentException(new StringBuilder().append("SQLInjection property: ").append(this.property).toString());
        }

        this.property = FieldConvertUtil.property2Field(this.property, clazz);
        return new StringBuilder().append(this.property).append(this.direction == null ? "" : new StringBuilder().append(" ").append(this.direction.name()).toString()).toString();
    }
}
