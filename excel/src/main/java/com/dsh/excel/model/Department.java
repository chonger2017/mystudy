package com.dsh.excel.model;

import java.util.List;

public class Department {
    private Integer id;

    private String name;

    private Integer fatherId;

    private String desciption;

    private Integer type;

    public Department(Integer id, String name, Integer fatherId, String desciption, Integer type) {
        this.id = id;
        this.name = name;
        this.fatherId = fatherId;
        this.desciption = desciption;
        this.type = type;
    }

    private List<Department> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Department> getList() {
        return list;
    }

    public void setList(List<Department> list) {
        this.list = list;
    }
}