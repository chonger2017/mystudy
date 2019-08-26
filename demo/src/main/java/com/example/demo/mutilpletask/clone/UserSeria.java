package com.example.demo.mutilpletask.clone;

import java.io.Serializable;

public class UserSeria implements Serializable {
    private String name;
    private AddressSeria addressSeria;

    public UserSeria(String name, AddressSeria addressSeria) {
        this.name = name;
        this.addressSeria = addressSeria;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressSeria getAddressSeria() {
        return addressSeria;
    }

    public void setAddressSeria(AddressSeria addressSeria) {
        this.addressSeria = addressSeria;
    }
}
