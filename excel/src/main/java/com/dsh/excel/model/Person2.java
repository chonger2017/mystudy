package com.dsh.excel.model;

import java.io.Serializable;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-11-27_18:08
 */
public class Person2 implements Serializable {
    private String name;

    private Integer age;

    private Email2 email2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Email2 getEmail2() {
        return email2;
    }

    public void setEmail2(Email2 email2) {
        this.email2 = email2;
    }

    public Person2(String name, Integer age, Email2 email2) {
        this.name = name;
        this.age = age;
        this.email2 = email2;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
