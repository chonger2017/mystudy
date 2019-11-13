package cn.web.bike.webbike.service;

import cn.web.bike.webbike.domain.Bike;
import cn.web.bike.webbike.domain.BikeMongoVO;
import org.springframework.data.geo.GeoResult;

import java.util.List;

public interface BikeService {

    public void save(Bike bike);

    public List<Bike> getAll();

    public List<GeoResult<BikeMongoVO>> getNear(double longitude, double latitude);
}
