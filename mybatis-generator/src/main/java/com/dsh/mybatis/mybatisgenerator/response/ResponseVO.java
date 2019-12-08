package com.dsh.mybatis.mybatisgenerator.response;

import lombok.Data;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-08_13:55
 */
@Data
public class ResponseVO<T> {
    private int status;

    private String message;

    private T data;

    public ResponseVO(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseVO<T> respok(T data){
        return new ResponseVO<>(ResponseDictionary.SUCCESS.getStatus(), ResponseDictionary.SUCCESS.getMessage(), data);
    }
}
