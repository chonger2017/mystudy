package com.imooc.mapper;

import com.imooc.dataobject.ProductInfo;
import com.imooc.mapper.sql.GetProductInfoSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoDao {

    @Select("select * from product_info where product_id = #{productId}")
    ProductInfo findOneById(@Param("productId") Integer productId);

    @Select("select * from product_info where product_status = #{productStatus}")
    List<ProductInfo> findOneByStatus(@Param("productStatus") Integer productStatus);

    @Select("select * from product_info")
    List<ProductInfo> findUpAll();

    @Select("select * from product_info limit #{page}, #{pageSize} ")
    List<ProductInfo> findAll(@Param("page") Integer page, @Param("pageSize") Integer pageSize);

    @Insert("insert into product_info (product_id,product_name,product_price,product_stock,product_description," +
            "product_icon,product_status,category_type,create_time,update_time) " +
            "values(#{productId}, #{productName}, #{productPrice}, #{productStock}, #{productDescription}, #{productIcon}, #{productStatus}, #{categoryType}, now(),now())")
    void save(ProductInfo productInfo);

    @UpdateProvider(method = "getUpdateProductInfoSql", type = GetProductInfoSql.class)
    void updateProductInfo(ProductInfo productInfo);

    @Select("select count(0) from product_info")
    Integer getCount();
}
