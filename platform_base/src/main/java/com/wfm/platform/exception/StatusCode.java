package com.wfm.platform.exception;

public class StatusCode {

    //成功
    public static final int OK = 20000;
    //失败
    public static final int ERROR = 20001;
    //用户名或密码错误
    public static final int LOGINERROR = 20002;
    //权限不足
    public static final int ACCESSERROR = 20003;
    //远程调用失败
    public static final int REMOTEERROR = 20004;
    //重复操作
    public static final int REPERROR = 20005;
    //token不合法
    public static final int ILLEGAL_TOKEN = 50008;
    //token过期
    public static final int TOKEN_EXPIRED = 50014;
    //其他客户端登录
    public static final int OTHER_CLIENTS_LOGIN = 50012;


}
