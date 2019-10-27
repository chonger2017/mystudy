package com.imooc.dto;

import lombok.Data;

@Data
public class CartDTO {
    /** 商品Id. */
    private Integer productId;

    /** 数量. */
    private Integer productQuantity;

    public CartDTO(Integer productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
