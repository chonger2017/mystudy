package com.dsh.daniel.xierqi.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-01-01_11:06
 */
public class SayHello extends UnicastRemoteObject implements ISayHello {

    protected SayHello() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello Daniel ->"+name;
    }
}
