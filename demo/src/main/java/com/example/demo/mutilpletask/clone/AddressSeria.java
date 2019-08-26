package com.example.demo.mutilpletask.clone;

import java.io.Serializable;

public class AddressSeria implements Serializable {
    private String city;
    private String country;

    public AddressSeria(String name, String country) {
        this.city = name;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
