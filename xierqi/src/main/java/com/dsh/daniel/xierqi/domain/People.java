package com.dsh.daniel.xierqi.domain;

import lombok.Data;

@Data
public class People {
    private Double lat;
    private Double lon;
    private String wxNo;
    private String nickName;
    private String sex;

    public People(Double lat, Double lon, String wxNo, String nickName, String sex) {
        this.lat = lat;
        this.lon = lon;
        this.wxNo = wxNo;
        this.nickName = nickName;
        this.sex = sex;
    }
}
