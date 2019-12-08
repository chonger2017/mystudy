package com.dsh.mybatis.mybatisgenerator.response;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-08_14:01
 */
public enum ResponseDictionary {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "INTERNAL ERROR"),
    ;

    private int status;
    private String message;

    ResponseDictionary(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }}
