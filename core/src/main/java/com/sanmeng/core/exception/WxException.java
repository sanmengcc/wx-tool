package com.sanmeng.core.exception;

import java.text.MessageFormat;

/**
 * @Author：胡侯东
 * @Date：2021/4/28 9:58 上午
 * @Desc:
 */
public class WxException extends RuntimeException{
    private static final long serialVersionUID = 6961926234532027069L;
    private String code;

    public WxException(String message) {
        super(message);
    }

    public WxException(Throwable cause) {
        super(cause);
    }

    public WxException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxException() {
    }

    public WxException(String code, String msg) {
        this(msg);
        this.code = code;
    }

    public WxException(Enum<?> en) {
        this(en.name(), en.toString());
    }

    public WxException(Enum<?> en, Object... args) {
        this(en.name(), MessageFormat.format(en.toString(), args));
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}