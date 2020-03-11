package com.dsh.excel.excel.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-11_22:22
 */
public abstract class ExportChain {
    public abstract void export(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
