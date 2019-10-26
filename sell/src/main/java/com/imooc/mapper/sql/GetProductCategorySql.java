package com.imooc.mapper.sql;

import com.imooc.dataobject.ProductCategory;
import org.apache.commons.lang3.StringUtils;

public class GetProductCategorySql {

    public String getUpdateProductCategorySql(ProductCategory productCategory) {
        StringBuilder sqlbuilder = new StringBuilder("update product_category set ");
        if (StringUtils.isNotBlank(productCategory.getCategoryName())) {
            sqlbuilder.append("category_name = #{categoryName},");
        }
        if (null != productCategory.getCategoryType()) {
            sqlbuilder.append("category_type = #{categoryType},");
        }
        sqlbuilder.append("update_time = #{updateTime}");
        sqlbuilder.append(" where category_id = #{categoryId}");
        return sqlbuilder.toString();
    }
}
