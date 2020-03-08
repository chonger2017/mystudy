package com.dsh.excel.exception;

public class BusinessException extends RuntimeException {

    private Object code;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Object code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Object code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }
}
