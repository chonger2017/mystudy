package com.dsh.excel.excel;

import com.dsh.excel.model.Department;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-10_0:43
 */
public class Test {

    public static void main(String[] args) {
        List<Department> list = generateList();
        System.out.println(list);
    }

    public static List<Department> generateList() {
        List<Department> list = Arrays.asList(
                new Department(1, "AAA1", 0, "aaa1", 1),
                new Department(2, "BBB1", 1, "bbb1", 1),
                new Department(3, "BBB2", 1, "bbb2", 1),
                new Department(4, "CCC1", 2, "ccc1", 1),
                new Department(5, "CCC2", 2, "ccc2", 1),
                new Department(6, "CCC3", 3, "ccc3", 1),
                new Department(7, "CCC4", 3, "ccc4", 1),
                new Department(8, "AAA2", 0, "aaa2", 1),
                new Department(9, "BBB3", 8, "bbb3", 1),
                new Department(10, "BBB4", 8, "bbb4", 1)
        );
        return listToTree(list);
    }

    public static List<Department> listToTree(List<Department> oldlist) {
        Map<Integer, Department> map = oldlist.stream().collect(Collectors.toMap(Department::getId, ele -> ele));
        List<Department> newlist = new ArrayList<>();
        for (Department temp : oldlist) {
            Department parent = map.get(temp.getFatherId());
            if (parent != null) {
                if (CollectionUtils.isEmpty(parent.getList())) {
                    List<Department> ch = new ArrayList<>();
                    ch.add(temp);
                    parent.setList(ch);
                } else {
                    List<Department> ch = parent.getList();
                    ch.add(temp);
                    parent.setList(ch);
                }
            } else {
                newlist.add(temp);
            }
        }
        return newlist;
    }
}
