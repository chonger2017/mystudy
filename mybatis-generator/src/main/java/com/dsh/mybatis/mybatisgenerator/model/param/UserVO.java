package com.dsh.mybatis.mybatisgenerator.model.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-11-04_13:10
 */
public class UserVO {
    @NotNull(message = "用户id不能为空")
    private Integer id;

    private String username;

    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
    private String passwd;

    private String email;

    private Integer grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
