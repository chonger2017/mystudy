package com.dsh.excel.excel;

import com.dsh.excel.excel.common.GroupRowCommand;
import com.dsh.excel.excel.common.JxlsTemplate;
import com.dsh.excel.model.Department;
import com.dsh.excel.model.Test;
import com.dsh.excel.model.User;
import com.dsh.excel.service.IDepartmentService;
import com.dsh.excel.service.ITestService;
import com.dsh.excel.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @Autowired
    private IDepartmentService fileService;

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

    @RequestMapping("/exportFile")
    public void exportFile(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        String exportTemplate = request.getParameter("exportTemplate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String format = sdf.format(new Date());
        try {
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Content-Disposition", "attachment; filename=\""+format+".xlsx\"");
            response.setHeader("Pragma", "public");
            response.setContentType("application/x-excel;charset=UTF-8");
            ApplicationHome h = new ApplicationHome(getClass());
            String dirPath = h.getSource().getParentFile().toString();
            System.out.println(dirPath+"/classes/excel/"+exportTemplate);
            String path = dirPath + "/classes/excel/" + exportTemplate;
            InputStream in = new FileInputStream(path);
            out = response.getOutputStream();
            Transformer transformer = TransformerFactory.createTransformer(in, out);
            AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
            /**添加自定义命令**/
            XlsCommentAreaBuilder.addCommandMapping("groupRow", GroupRowCommand.class);
            List<Area> xlsAreaList = areaBuilder.build();
            Area xlsArea = xlsAreaList.get(0);
            Context context = new Context();
            List<Department> all = generateList();
            context.putVar("files", all);
            xlsArea.applyAt(new CellRef("file!A1"), context);
            transformer.write();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Department> generateList() {
        List<Department> clist1 = Arrays.asList(
                new Department(4,"CCC1",2, "aaa1",1),
                new Department(5,"CCC2",2, "aaa1",1)
        );
        List<Department> clist2 = Arrays.asList(
                new Department(6, "CCC3", 3, "ccc3", 1),
                new Department(7, "CCC4", 3, "ccc4", 1));
        Department b1 = new Department(2, "BBB1", 1, "bbb1", 1);
        b1.setList(clist1);
        Department b2 = new Department(3, "BBB2", 1, "bbb2", 1);
        b2.setList(clist2);
        Department a1 = new Department(1, "AAA1", 0, "aaa1", 1);
        List<Department> l1 = Arrays.asList(b1, b2);
        a1.setList(l1);
        List<Department> blist2 = Arrays.asList( new Department(9,"BBB3",8, "aaa1",1),
                new Department(10,"BBB4",8, "aaa1",1));
        Department a2 = new Department(11, "AAA2", 8, "aaa2", 1);
        a2.setList(blist2);
        List<Department> list = Arrays.asList(a1,a2);
        return list;
    }
}
