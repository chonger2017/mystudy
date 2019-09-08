package com.dsh.daniel.xierqi.domain;

import com.dsh.daniel.xierqi.annotation.Check;
import lombok.Data;

@Data
public class User {
    private String name;

    @Check(paramValues = {"man", "woman"})
    private String sex;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
