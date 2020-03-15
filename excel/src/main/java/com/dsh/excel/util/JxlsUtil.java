package com.dsh.excel.util;

import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-15_11:12
 */
public class JxlsUtil {
    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> beans) throws IOException {
        Context context = new Context();
        if (beans != null) {
            for (String key : beans.keySet()) {
                context.putVar(key, beans.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer  = jxlsHelper.createTransformer(is, os);
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
        Map<String, Object> funcs = new HashMap<String, Object>();
        funcs.put("jx", new JxlsUtil());    //添加自定义功能
        evaluator.getJexlEngine().setFunctions(funcs);
        jxlsHelper.processTemplate(context, transformer);
    }
}
