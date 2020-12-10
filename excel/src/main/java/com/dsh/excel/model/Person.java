package com.dsh.excel.model;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-11-27_18:08
 */
public class Person implements Cloneable{
    private String name;

    private Integer age;

    private Email email;

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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Person(String name, Integer age, Email email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
