package com.dsh.daniel.xierqi.controller.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-01-05_15:27
 */
public class User_Skeleton extends Thread {
    private UserServer userServer;

    public User_Skeleton(UserServer userServer) {
        this.userServer = userServer;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            Socket accept = serverSocket.accept();
            while (accept != null) {
                ObjectInputStream read = new ObjectInputStream(accept.getInputStream());
                String method = (String) read.readObject();
                if (method.equals("age")) {
                    int age = userServer.getAge();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
                    objectOutputStream.writeInt(age);
                    objectOutputStream.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
