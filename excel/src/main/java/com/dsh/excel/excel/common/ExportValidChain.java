package com.dsh.excel.excel.common;

import com.dsh.excel.util.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-11_22:25
 */
public abstract class ExportValidChain extends ExportChain{
    protected ExportDataProvider exportDataProvider;

    protected Map<String, String[]> parameters;

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        validParameter(request);
        export1(request, response);
    }

    private void validParameter(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        String[] exportTemplate =  map.get("exportTemplate");
        if (exportTemplate == null || StringUtils.isBlank(exportTemplate[0])) {
            throw new RuntimeException("exportTemplate is null");
        }
        String[] exportDataParam = map.get("exportDataProvider");
        if (exportDataParam == null || StringUtils.isBlank(exportDataParam[0])) {
            throw new RuntimeException("exportDataProvider is null");
        }
        this.parameters = map;
        String beanName = exportDataParam[0];
        exportDataProvider = SpringContextUtils.getBean(beanName, ExportDataProvider.class);
    }

    public abstract void export1(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
