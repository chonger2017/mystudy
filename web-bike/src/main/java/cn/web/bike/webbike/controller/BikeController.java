package cn.web.bike.webbike.controller;

import cn.web.bike.webbike.domain.Bike;
import cn.web.bike.webbike.domain.BikeMongoVO;
import cn.web.bike.webbike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @RequestMapping("/bike/add")
    @ResponseBody
    public Bike save(@RequestBody Bike bike) {
//        System.out.println(bike.getLongitude()+"=="+bike.getLatitude());
        bikeService.save(bike);
        return bike;
    }

    @RequestMapping("/bike/getAll")
    @ResponseBody
    public List<Bike> getAll() {
        return bikeService.getAll();
    }

    @RequestMapping("/bike/getNear")
    @ResponseBody
    public List<GeoResult<BikeMongoVO>> getNear(double longitude, double latitude) {
        return bikeService.getNear(longitude,latitude);
    }
}
