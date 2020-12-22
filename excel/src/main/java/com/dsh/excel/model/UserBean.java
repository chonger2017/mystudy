package com.dsh.excel.model;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-12-22_23:46
 */
public class UserBean {
    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
