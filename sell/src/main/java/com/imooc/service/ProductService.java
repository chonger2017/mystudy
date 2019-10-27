package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(Integer productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    List<ProductInfo> findAll(Integer page, Integer pageSize);

    ProductInfo update(ProductInfo productInfo);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(Integer productId);

    //下架
    ProductInfo offSale(Integer productId);

    Integer getCount();
}
