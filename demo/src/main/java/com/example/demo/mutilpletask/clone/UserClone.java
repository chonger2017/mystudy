package com.example.demo.mutilpletask.clone;

public class UserClone implements Cloneable{
    private String name;
    private AddressClone address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressClone getAddress() {
        return address;
    }

    public void setAddress(AddressClone address) {
        this.address = address;
    }

    public UserClone(String name, AddressClone address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected UserClone clone() throws CloneNotSupportedException {
        UserClone user = (UserClone) super.clone();
        user.setAddress(this.address.clone());
        return user;
    }
}
