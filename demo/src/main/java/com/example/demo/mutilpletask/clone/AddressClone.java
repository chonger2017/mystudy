package com.example.demo.mutilpletask.clone;

public class AddressClone implements Cloneable{
    private String city;
    private String country;

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

    public AddressClone(String city, String country) {
        this.city = city;
        this.country = country;
    }

    @Override
    protected AddressClone clone() throws CloneNotSupportedException {
        return (AddressClone) super.clone();
    }
}
