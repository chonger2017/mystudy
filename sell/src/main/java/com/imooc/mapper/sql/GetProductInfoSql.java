package com.imooc.mapper.sql;

import com.imooc.dataobject.ProductInfo;
import org.apache.commons.lang3.StringUtils;

public class GetProductInfoSql {

    public String getUpdateProductInfoSql(ProductInfo productInfo) {
        StringBuilder sqlBuilder = new StringBuilder("update product_info set ");
        if (productInfo.getCategoryType() != null) {
            sqlBuilder.append("category_type = #{categoryType},");
        }
        if (productInfo.getProductStatus() != null) {
            sqlBuilder.append("product_status = #{productStatus},");
        }
        if (StringUtils.isNotBlank(productInfo.getProductIcon())) {
            sqlBuilder.append("product_icon = #{productIcon},");
        }
        if (StringUtils.isNotBlank(productInfo.getProductDescription())) {
            sqlBuilder.append("product_description = #{productDescription},");
        }
        if (productInfo.getProductStock() != null) {
            sqlBuilder.append("product_stock = #{productStock},");
        }
        if (productInfo.getProductPrice() != null) {
            sqlBuilder.append("product_price = #{productPrice},");
        }
        if (StringUtils.isNotBlank(productInfo.getProductName())) {
            sqlBuilder.append("product_name = #{productName},");
        }
        sqlBuilder.append("update_time = now() where product_id = #{productId}");
        return sqlBuilder.toString();
    }
}
