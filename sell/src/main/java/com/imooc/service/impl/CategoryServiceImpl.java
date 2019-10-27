package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import com.imooc.mapper.ProductCategoryDao;
import com.imooc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDao.getProductCategoryById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.getProductCategorys();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return null;
    }

    @Override
    public Integer save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }

    @Override
    public void updateProductCategory(ProductCategory productCategory) {
        productCategoryDao.updateProductCategory(productCategory);
    }
}
