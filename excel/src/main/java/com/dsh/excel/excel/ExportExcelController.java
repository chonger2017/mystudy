package com.dsh.excel.excel;

import com.dsh.excel.excel.common.JxlsTemplate;
import com.dsh.excel.exception.BusinessException;
import com.dsh.excel.model.Test;
import com.dsh.excel.model.User;
import com.dsh.excel.service.ITestService;
import com.dsh.excel.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_15:26
 */
@RequestMapping("/excel")
@Controller
public class ExportExcelController extends JxlsTemplate {
    @Autowired
    private IUserService userService;

    @Autowired
    private ITestService testService;

    @RequestMapping("/export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        List<User> all = userService.getAll();
        List<Test> tests = testService.getAll();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("users", all);
        params.put("tests", tests);
        ServletOutputStream out = null;
        String exportTemplate = request.getParameter("exportTemplate");
        if (StringUtils.isBlank(exportTemplate)) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String format = sdf.format(new Date());
        try {
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Content-Disposition", "attachment; filename=\""+format+".xlsx\"");
            response.setHeader("Pragma", "public");
            response.setContentType("application/x-excel;charset=UTF-8");
            out = response.getOutputStream();
            JxlsTemplate.processTemplate(exportTemplate, out, params);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
