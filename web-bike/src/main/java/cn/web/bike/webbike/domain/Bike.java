package cn.web.bike.webbike.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Bike {

    @Id
    private Long id;
    private Integer status;
    private Double longitude;
    private Double latitude;
//    //表示经纬度的索引[经度，纬度]
//    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
//    private double[] location;
    private Long bikeNo;
}
