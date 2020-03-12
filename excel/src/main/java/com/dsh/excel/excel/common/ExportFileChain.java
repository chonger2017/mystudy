package com.dsh.excel.excel.common;

import org.apache.commons.jexl2.UnifiedJEXL;
import org.springframework.boot.system.ApplicationHome;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-11_22:25
 */
public abstract class ExportFileChain extends ExportValidChain{
    private final static String SOURCE_DIR = "/classes/excel/";

    private InputStream in;

    private OutputStream os;

    private File tempFile;

    @Override
    public void export1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        createFileInputStream(request);
        export2(in, os, request.getParameterMap());
        closeInputStream();
    }

    public void createFileInputStream(HttpServletRequest request) throws Exception {
        ApplicationHome h = new ApplicationHome(ExportFileChain.class);
        String dirPath = h.getSource().getParentFile().toString();
        String[] param = request.getParameterMap().get("exportTemplate");
        String s = param[0];
        String path = dirPath + SOURCE_DIR + s;
        in = new FileInputStream(path);
        if(in == null) {
            throw new RuntimeException("can't find excel template by path:" + path);
        }
    }

    public void setOutputStream(HttpServletResponse response) throws IOException {
        os = response.getOutputStream();
    }

    public InputStream getInputStream() {
        return in;
    }

    public OutputStream getOutputStream() {
        return os;
    }

    public void serResponseFile(HttpServletResponse response) {
        String fileName = getFileName();
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".xlsx\"");
        response.setHeader("Pragma", "public");
        response.setContentType("application/x-excel;charset=UTF-8");
    }

    private String getFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String format = sdf.format(new Date());
        return format;
    }

    private void closeInputStream() {
        if (in != null) {
            try {
                in.close();
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void export2(InputStream in, OutputStream os, Map<String, String[]> parameter) throws Exception;
}
