package com.dsh.excel.excel;

import com.dsh.excel.excel.common.ExportDataProvider;
import com.dsh.excel.model.Test;
import com.dsh.excel.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-16_23:40
 */
@Component("testDataProvider")
public class TestDataProvider extends ExportDataProvider {
    @Autowired
    private ITestService testService;
    @Override
    public Map<String, Object> getBusinessData(Map<String, String[]> map) {
        List<Test> all = testService.getAll();
        Map<String, Object> result = new HashMap<>();
        result.put("tests", all);
        return result;
    }
}
