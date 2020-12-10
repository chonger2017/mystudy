package com.dsh.excel.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
    private Integer id;

    private Integer nums;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        /*Email email = new Email("123@qq.com", 12);
        Person person = new Person("hello", 12, email);
        try {
            Person ccc = (Person) person.clone();
            System.out.println(ccc.getEmail() == person.getEmail());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }*/


        Email2 email = new Email2("123@qq.com", 12);
        Person2 person = new Person2("hello", 12, email);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(person);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Person2 object = (Person2) ois.readObject();
            System.out.println(object == person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}