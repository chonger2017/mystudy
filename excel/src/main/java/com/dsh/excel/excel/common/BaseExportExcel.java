package com.dsh.excel.excel.common;

import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_13:49
 */
public abstract class BaseExportExcel {
    public abstract List<?> getExcelData();
}
