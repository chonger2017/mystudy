package com.dsh.daniel.xierqi.controller.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-01-05_15:27
 */
public class User_Stub extends User {
    private Socket socket;

    public User_Stub() throws IOException {
        socket = new Socket("localhost", 8888);
    }

    public int getAge() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject("age");
        oos.flush();

        ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
        return objectInput.readInt();
    }
}
