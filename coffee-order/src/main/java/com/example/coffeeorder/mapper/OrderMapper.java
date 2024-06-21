package com.example.coffeeorder.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.coffeeorder.entity.Order;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Insert("INSERT INTO orderdemo (orderuserid, totalprice, userphone, donestatus, paystatus, address) " +
            "VALUES (#{orderuserid}, #{totalprice}, #{userphone}, #{donestatus}, #{paystatus}, #{address})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "orderid", resultType = Integer.class, before = false)
    int insertAndReturnId(Order order);

    @Select("SELECT * FROM orders WHERE donestatus = '未完成'")
    List<Order> selectOrderHistory();
}