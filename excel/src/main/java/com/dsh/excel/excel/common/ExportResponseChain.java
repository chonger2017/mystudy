package com.dsh.excel.excel.common;

import com.dsh.excel.excel.common.ExportFileChain;
import com.dsh.excel.util.JxlsUtil;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-12_0:04
 */
@Component
public class ExportResponseChain extends ExportFileChain {
    @Override
    public void export2(HttpServletResponse response, Map<String, String[]> parameter) throws Exception {
        serResponseFile(response);
        Map<String, Object> businessData = this.exportDataProvider.getBusinessData(parameter);
        JxlsUtil.exportExcel(this.in, this.os, businessData);
    }

    public void serResponseFile(HttpServletResponse response) {
        String fileName = getFileName();
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
        response.setHeader("Pragma", "public");
        response.setContentType("application/x-excel;charset=UTF-8");
    }

    private String getFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String format = sdf.format(new Date());
        return format+suffix;
    }

}
