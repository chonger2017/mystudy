package com.dsh.daniel.xierqi.domain.VO;

import com.dsh.daniel.xierqi.util.ResponseCode;

import java.io.Serializable;

public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -546322477321053482L;

    private int status;

    private T data;

    private String message;

    public ResponseVO(int status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ResponseVO res_ok() {
        return new ResponseVO(ResponseCode.RESPONSE_OK.getCode(), null, ResponseCode.RESPONSE_OK.getMessage());
    }

    public static <T> ResponseVO res_ok(T data) {
        return new ResponseVO(ResponseCode.RESPONSE_OK.getCode(), data, ResponseCode.RESPONSE_OK.getMessage());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
