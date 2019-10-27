package com.imooc.mapper;

import com.imooc.dataobject.ProductCategory;
import com.imooc.mapper.sql.GetProductCategorySql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryDao{

    @Select("select category_id, category_name, category_type, create_time, update_time " +
            "from product_category where category_id=#{categoryId}")
    ProductCategory getProductCategoryById(@Param("categoryId") Integer categoryId);

    @Select("select category_id, category_name, category_type, create_time, update_time " +
            "from product_category")
    List<ProductCategory> getProductCategorys();

    @Insert("insert into product_category (category_name, category_type, create_time, update_time) " +
            "values(#{categoryName}, #{categoryType}, now(), now())")
    Integer save(ProductCategory productCategory);

    @UpdateProvider(method = "getUpdateProductCategorySql", type = GetProductCategorySql.class)
    void updateProductCategory(ProductCategory productCategory);
}
