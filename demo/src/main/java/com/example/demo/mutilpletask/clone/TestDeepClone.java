package com.example.demo.mutilpletask.clone;

import org.apache.commons.lang3.SerializationUtils;

public class TestDeepClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         * 深度克隆和浅度克隆
         * 深度克隆就是拷贝了源对象的所有值，浅度克隆就是拷贝了源对象的地址
         */
        /*AddressClone address = new AddressClone("杭州", "中国");
        AddressClone copyAddress = (AddressClone) address.clone();
        copyAddress.setCity("深圳");
        System.out.println(address.getCity()==copyAddress.getCity());
        UserClone user = new UserClone("大山", address);

        //用clone方法进行深度拷贝
        UserClone copyUser = user.clone();
        user.getAddress().setCity("深圳");
        System.out.println(user.getAddress().getCity() +","+ copyUser.getAddress().getCity());*/

        AddressSeria addressSeria = new AddressSeria("杭州", "中国");
        UserSeria userSeria = new UserSeria("大山", addressSeria);
        UserSeria copyUser = (UserSeria) SerializationUtils.clone(userSeria);

        copyUser.getAddressSeria().setCity("深圳");
        System.out.println(userSeria.getAddressSeria().getCity()+","+copyUser.getAddressSeria().getCity());
    }
}
