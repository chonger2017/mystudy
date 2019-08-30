package com.dsh.daniel.xierqi.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class SearchResult {
    private Long total;

    private Float useTime;

    private String distance;

    private List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();//数据集合
}
