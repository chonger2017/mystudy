package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 类目
 * Created by 廖师兄
 * 2017-05-07 14:30
 */
@Data
@Table(name="product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = -2308267959836189694L;
    /**
     * 类目id.
     */
    private Integer categoryId;

    /**
     * 类目名字.
     */
    private String categoryName;

    /**
     * 类目编号.
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
