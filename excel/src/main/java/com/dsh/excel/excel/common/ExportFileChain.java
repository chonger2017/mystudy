package com.dsh.excel.excel.common;

import org.apache.commons.jexl2.UnifiedJEXL;
import org.springframework.boot.system.ApplicationHome;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-11_22:25
 */
public abstract class ExportFileChain extends ExportValidChain{
    private final static String SOURCE_DIR = "/classes/excel/";

    protected InputStream in;

    protected OutputStream os;

    protected File tempFile;

    protected String suffix;

    @Override
    public void export1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        createFileInputStream(request);
        createTempFile();
        createOutputStream();
        export2(response, request.getParameterMap());
        close();
    }

    public void createTempFile() {
        ApplicationHome h = new ApplicationHome(ExportFileChain.class);
        Map<String, String[]> param = this.parameters;
        String fileName = param.get("exportTemplate")[0];
        this.suffix = fileName.substring(fileName.indexOf("."), fileName.length());
        String dirPath = h.getSource().getParentFile().toString();
        String path = dirPath + "/" + UUID.randomUUID() + "/"+System.currentTimeMillis()+suffix;
        tempFile = new File(path);
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

    public void createOutputStream() {
        try {
            os = new FileOutputStream(tempFile);
        } catch (FileNotFoundException e) {
        }
    }

    private void closeInputStream() {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeOutputStream() {
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deletefile() {
        tempFile.delete();
    }

    private void close() {
        closeOutputStream();
        closeInputStream();
        deletefile();
    }

    public abstract void export2(HttpServletResponse response, Map<String, String[]> parameter) throws Exception;


}
