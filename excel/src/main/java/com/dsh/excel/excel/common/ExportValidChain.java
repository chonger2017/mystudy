package com.dsh.excel.excel.common;

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
    private Map<String, String[]> parameter;

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        validParameter(request);
        export1(request, response);
    }

    private void validParameter(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        String[] exportTemplate = (String[]) map.get("exportTemplate");
        if (exportTemplate == null) {
            throw new RuntimeException("exportTemplate is null");
        }
        this.parameter = map;
    }

    public abstract void export1(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
