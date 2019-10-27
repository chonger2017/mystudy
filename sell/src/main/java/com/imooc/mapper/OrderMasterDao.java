package com.imooc.mapper;

import com.imooc.dataobject.OrderMaster;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMasterDao {

    @Select("select order_id, buyer_name, buyer_phone, buyer_address, buyer_openid, order_amount, order_status, pay_status, create_time, update_time " +
            "from order_master where buyer_openid = #{buyerOpenid} limit #{start}, #{pageSize}")
    List<OrderMaster> findByBuyerOpenid(@Param("buyerOpenid") String buyerOpenid, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    @Insert("insert into order_master(order_id, buyer_name, buyer_phone, buyer_address, buyer_openid, order_amount, order_status, pay_status, create_time, update_time) " +
            "values(#{orderId},#{buyerName},#{buyerPhone},#{buyerAddress},#{buyerOpenid},#{orderAmount},#{orderStatus},now(),now())")
    void save(OrderMaster orderMaster);

    @Select("select order_id, buyer_name, buyer_phone, buyer_address, buyer_openid, order_amount, order_status, pay_status, create_time, update_time " +
            "from order_master where order_id = #{orderId}")
    OrderMaster findOne(@Param("orderId") String orderId);

    @Select("select order_id, buyer_name, buyer_phone, buyer_address, buyer_openid, order_amount, order_status, pay_status, create_time, update_time " +
            "from order_master limit #{start}, #{pageSize} ")
    List<OrderMaster> findAll(@Param("#{page}") Integer page, @Param("#{pageSize}")Integer pageSize);
}
