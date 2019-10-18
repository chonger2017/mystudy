package com.imooc.mapper;

import com.imooc.dataobject.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryDao {

    ProductCategory getProductCategoryById(@Param("categoryId") Integer categoryId);

    List<ProductCategory> getProductCategorys();

    ProductCategory save(@Param("productCategory") ProductCategory productCategory);

    void updateProductCategory(@Param("productCategory") ProductCategory productCategory);
}
