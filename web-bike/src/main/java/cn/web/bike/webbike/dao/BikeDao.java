package cn.web.bike.webbike.dao;

import cn.web.bike.webbike.domain.Bike;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BikeDao {

    @Insert("insert into t_bikes(status,longitude,latitude) values(#{status},#{longitude},#{latitude})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void save(Bike bike);

    @Select("select id, longitude, latitude, status from t_bikes")
    List<Bike> getAll();
}
