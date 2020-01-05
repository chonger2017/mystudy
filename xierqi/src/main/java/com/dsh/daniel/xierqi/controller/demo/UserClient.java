package com.dsh.daniel.xierqi.controller.demo;

import java.io.IOException;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-01-05_15:40
 */
public class UserClient {
    public static void main(String[] args) {
        try {
            User user = new User_Stub();
            int age = user.getAge();
            System.out.println(age);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
