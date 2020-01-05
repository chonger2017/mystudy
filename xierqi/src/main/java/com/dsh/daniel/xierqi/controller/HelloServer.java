package com.dsh.daniel.xierqi.controller;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-01-01_11:12
 */
public class HelloServer {
    public static void main(String[] args) {
        try {
            ISayHello hello = new SayHello();
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://localhost:8888/sayHello", hello);

            System.out.println("server start success");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
