package com.dsh.excel.excel.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-12_23:27
 */
public abstract class ExportDataProvider {
    public abstract Map<String, Object> getBusinessData(Map<String, String[]> map);

    public String getFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String format = sdf.format(new Date());
        return format;
    }
}
