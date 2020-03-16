package com.dsh.excel.excel;

import com.dsh.excel.excel.common.ExportChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_15:26
 */
@RequestMapping("/excel")
@Controller
public class ExportExcelController{
    @Autowired
    private ExportChain exportChain;

    @RequestMapping("/export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            exportChain.export(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
