package com.dsh.daniel.xierqi.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-01-05_14:29
 */
public class HelloClient {
    public static void main(String[] args) {
        try {
            ISayHello hello  = (ISayHello) Naming.lookup("rmi://localhost:8888/sayHello");
//            System.out.println(hello);
            System.out.println(hello.sayHello("beautiful"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
