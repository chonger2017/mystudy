package com.dsh.daniel.xierqi.controller.demo;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-01-05_15:31
 */
public class UserServer extends User {
    public static void main(String[] args) {
        UserServer userServer = new UserServer();
        userServer.setAge(18);

        User_Skeleton user_skeleton = new User_Skeleton(userServer);
        user_skeleton.start();
    }
}
