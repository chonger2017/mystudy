package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class SellerInfo {

    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
