package com.dsh.excel.excel;

import com.dsh.excel.excel.common.ExportFileChain;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-12_0:04
 */
@Component
public class ExportDataProvider extends ExportFileChain {
    @Override
    public void export2(InputStream in, OutputStream os, Map<String, String[]> parameter) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map = getBusiness(parameter);
        Context context = new Context();
        if (map != null) {
            for (String key : map.keySet()) {
                context.putVar(key, map.get(key));
            }
        }
        JxlsHelper instance = JxlsHelper.getInstance();
        JxlsHelper.getInstance().processTemplate(in, os, context);
    }


    public Map<String, Object> getBusiness(Map<String, String[]> objectMap) throws Exception {
        return null;
    }
}
