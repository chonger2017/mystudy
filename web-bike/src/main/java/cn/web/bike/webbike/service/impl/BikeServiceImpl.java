package cn.web.bike.webbike.service.impl;

import cn.web.bike.webbike.dao.BikeDao;
import cn.web.bike.webbike.domain.Bike;
import cn.web.bike.webbike.domain.BikeMongoVO;
import cn.web.bike.webbike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {

    //按类型注入，到spring 容器中，查找bikeDao类型的实例，然后注入到BikeServiceImpl中
    @Autowired
    private BikeDao bikeDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Bike bike) {
        bikeDao.save(bike);
        BikeMongoVO bikeMongoVO = new BikeMongoVO();
        bikeMongoVO.setId(bike.getId());
        bikeMongoVO.setStatus(bike.getStatus());
        double[] locations = {bike.getLongitude(), bike.getLatitude()};
        bikeMongoVO.setLocation(locations);
        mongoTemplate.insert(bikeMongoVO);
    }

    @Override
    public List<Bike> getAll() {
        return bikeDao.getAll();
    }

    @Override
    public List<GeoResult<BikeMongoVO>> getNear(double longitude, double latitude) {
        //查找全部的单车
//        return mongoTemplate.findAll(Bike.class);
        //根据经纬度查找附近的单车
        NearQuery nearQuery = NearQuery.near(longitude,latitude);
        //查找的范围和距离单位
        nearQuery.maxDistance(0.2, Metrics.KILOMETERS);
        GeoResults<BikeMongoVO> bikes = mongoTemplate.geoNear(nearQuery.query(new Query(Criteria.where("status").is(0))).limit(20), BikeMongoVO.class);
        return bikes.getContent();
    }

}
