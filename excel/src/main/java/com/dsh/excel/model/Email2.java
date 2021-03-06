package com.dsh.excel.model;

import java.io.Serializable;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-11-27_18:08
 */
public class Email2 implements Serializable {
    private String address;

    private Integer count;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Email2(String address, Integer count) {
        this.address = address;
        this.count = count;
    }

    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }*/
}
