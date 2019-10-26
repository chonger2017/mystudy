package com.imooc.service.impl;

import com.imooc.config.UpYunConfig;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.mapper.ProductInfoDao;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private UpYunConfig upYunConfig;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOneById(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findOneByStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findAll(Integer page, Integer pageSize) {
        List<ProductInfo> proList = productInfoDao.findAll(page, pageSize);
//        proList.stream().forEach(e -> e.addImageHost(upYunConfig.getImageHost()));
        return proList;
    }

    @Override
    public ProductInfo update(ProductInfo productInfo) {
        productInfoDao.updateProductInfo(productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        productInfoDao.save(productInfo);
        ProductInfo one = productInfoDao.findOneById(productInfo.getProductId());
        return one;
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = productInfoDao.findOneById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            productInfoDao.updateProductInfo(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = productInfoDao.findOneById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            productInfoDao.updateProductInfo(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoDao.findOneById(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoDao.updateProductInfo(productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoDao.findOneById(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfoDao.updateProductInfo(productInfo);
        return productInfo;
    }

    @Override
    public Integer getCount() {
        return productInfoDao.getCount();
    }
}
