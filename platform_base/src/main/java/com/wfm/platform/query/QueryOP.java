package com.wfm.platform.query;

/**
 * @author Weifengming
 * @description 比较条件
 * @date 2020/2/18
 */
public enum QueryOP {
    EQUAL("EQ", "=", "等于"),
    EQUAL_IGNORE_CASE("EIC", "=", "等于忽略大小写"),
    LESS("LT", "<", "小于"),
    GREAT("GT", ">", "大于"),
    LESS_EQUAL("LE", "<=", "小于等于"),
    GREAT_EQUAL("GE", ">=", "大于等于"),
    NOT_EQUAL("NE", "!=", "不等于"),
    LIKE("LK", "like", "相似"),
    LEFT_LIKE("LFK", "like", "左相似"),
    RIGHT_LIKE("RHK", "like", "右相似"),
    IS_NULL("ISNULL", "is null", "为空"),
    NOTNULL("NOTNULL", "is not null", "非空"),
    IN("IN", "in", "在...中"),
    BETWEEN("BETWEEN", "between", "在...之间");

    private String val;
    private String op;
    private String desc;

    private QueryOP(String _val, String _op, String _desc) {
        this.val = _val;
        this.op = _op;
        this.desc = _desc;
    }

    public String value() {
        return this.val;
    }

    public String op() {
        return this.op;
    }

    public String desc() {
        return this.desc;
    }

    public static QueryOP getByOP(String op) {
        for (QueryOP queryOP : values()) {
            if (queryOP.op().equals(op)) {
                return queryOP;
            }
        }
        return null;
    }

    public static QueryOP getByVal(String val) {
        for (QueryOP queryOP : values()) {
            if (queryOP.val.equals(val)) {
                return queryOP;
            }
        }
        return null;
    }
}
