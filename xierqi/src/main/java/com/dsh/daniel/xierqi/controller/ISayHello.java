package com.dsh.daniel.xierqi.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-01-01_11:04
 */
public interface ISayHello extends Remote {
    public String sayHello(String name) throws RemoteException;
}
