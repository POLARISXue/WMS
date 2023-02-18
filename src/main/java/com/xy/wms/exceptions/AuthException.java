package com.xy.wms.exceptions;

/**
 * 自定义参数异常
 */
public class AuthException extends RuntimeException {
    private Integer code=300;
    private String msg="用户权限异常!";


    public AuthException() {
        super("用户权限异常!");
    }

    public AuthException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AuthException(Integer code) {
        super("用户权限异常!");
        this.code = code;
    }

    public AuthException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
