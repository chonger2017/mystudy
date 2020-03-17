package com.dsh.excel.excel.common;

import com.dsh.excel.util.JxlsUtil;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-12_0:04
 */
@Component
public class ExportResponseChain extends ExportFileChain {
    @Override
    public void export2(HttpServletResponse response, Map<String, String[]> parameter) throws Exception {
        String fileName = this.exportDataProvider.getFileName()+suffix;
        serResponseFile(response, fileName);
        Map<String, Object> businessData = this.exportDataProvider.getBusinessData(parameter);
        JxlsUtil.exportExcel(this.in, this.os, businessData);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(tempFile));
        ServletOutputStream out = response.getOutputStream();
        xssfWorkbook.write(out);
        response.flushBuffer();
    }

    public void serResponseFile(HttpServletResponse response, String fileName) {
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
        response.setHeader("Pragma", "public");
        response.setContentType("application/x-excel;charset=UTF-8");
    }
}
