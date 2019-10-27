package com.imooc.mapper;

import com.imooc.dataobject.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDao {

    @Select("select order_id, product_id, product_name, product_price, product_quantity, product_icon, create_time, update_time " +
            "from order_detail where order_id=#{orderId}")
    List<OrderDetail> findByOrderId(String orderId);

    @Insert("insert into order_detail(detail_id, order_id, product_id, product_name,product_price,product_quantity,product_icon,create_time,update_time) " +
            "values(#{detailId},#{orderId},#{productId},#{productName},#{productPrice},#{productQuantity},#{productIcon},#{createTime},#{updateTime})")
    void save(OrderDetail orderDetail);
}
