package com.dsh.excel.model;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-11-27_18:08
 */
public class Email implements Cloneable{
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

    public Email(String address, Integer count) {
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
