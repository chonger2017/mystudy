package com.dsh.excel.excel.common;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_17:19
 */
@Component
public abstract class JxlsTemplate {
    private static final String TEMPLATE_DIR = "/excel/";

    public static void processTemplate(String template, OutputStream out, Map<String, Object> params) throws IOException {
        processTemplate(JxlsTemplate.class, template, out, params);
    }

    public static void processTemplate(Class resourceBaseClass, String template, OutputStream out, Map<String, Object> params) throws IOException {
        ApplicationHome h = new ApplicationHome(resourceBaseClass);
        String dirPath = h.getSource().getParentFile().toString();
        System.out.println(dirPath+"/classes/"+TEMPLATE_DIR+template);
        String path = dirPath + "/classes/" + TEMPLATE_DIR + template;
        InputStream in = new FileInputStream(path);
//        InputStream in = resourceBaseClass.getResourceAsStream(TEMPLATE_DIR + template);
        if (null == in) {
          System.out.println("can't find excel template by path:" + TEMPLATE_DIR + template);
        }
        processTemplate(in, out, params);
    }

    private static void processTemplate(InputStream templateStream, OutputStream out, Context context) throws IOException {
        JxlsHelper.getInstance().processTemplate(templateStream, out, context);
    }

    public static void processTemplate(InputStream templateStream, OutputStream out, Map<String, Object> params) throws IOException {
        Context context = new Context();
        if (params != null) {
            for (String key : params.keySet()) {
              context.putVar(key, params.get(key));
            }
        }
        processTemplate(templateStream, out, context);
    }
}
