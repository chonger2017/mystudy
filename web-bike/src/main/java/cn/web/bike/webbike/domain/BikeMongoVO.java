package cn.web.bike.webbike.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bikes")
public class BikeMongoVO {

    @Id
    private Long id;
    private Integer status;
//    private Double longitude;
//    private Double latitude;
    //表示经纬度的索引[经度，纬度]
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private double[] location;
    @Indexed
    private Long bikeNo;
}
