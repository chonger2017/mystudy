package com.imooc.mapper;

import com.imooc.dataobject.SellerInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerInfoDao {

    @Select("select id, username, password, openid, create_time, update_time from seller_info where openid=#{openid} ")
    SellerInfo findByOpenId(@Param("openid") String openid);
}
