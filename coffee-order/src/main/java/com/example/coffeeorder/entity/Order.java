package com.example.coffeeorder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@TableName("orders")
public class Order {
    @TableId(value = " orderid", type = IdType.AUTO)
    private Integer orderid;//订单编号
    private Integer orderuserid;//订单用户id
    private double totalprice;//总价
    private String userphone;//用户号码
    private String donestatus;//订单完成状态
    private String paystatus;//支付状态
    private String address;//地址

    public void setId(Integer orderid) {
    }
}

