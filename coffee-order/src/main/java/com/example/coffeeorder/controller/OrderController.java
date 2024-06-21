package com.example.coffeeorder.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.coffeeorder.entity.Order;
import com.example.coffeeorder.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping()
    public List<Order> getOrder() {
        return orderMapper.selectList(null);
    }

    @DeleteMapping("/{orderid}")
    public int deleteOrder(@PathVariable("orderid") Integer orderid) {
        return orderMapper.deleteById(orderid);
    }

    @GetMapping("/search/{orderuserid}")
    public List<Order> findByOrderUserId(@PathVariable("orderuserid") String orderuserid) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("orderuserid", orderuserid);
        return orderMapper.selectList(queryWrapper);
    }

    @GetMapping("/getById/{orderid}")
    public Order findById(@PathVariable("orderid") Integer orderid) {
        return orderMapper.selectById(orderid);
    }

    @PutMapping("/{orderid}")
    public Order updateOrder(@PathVariable("orderid") Integer orderid, @RequestBody Order order) {
        order.setOrderid(orderid);
        orderMapper.updateById(order);
        return order;
    }

    @GetMapping("/findByPage")
    public IPage<Order> findByPage(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam(value = "orderuserid", required = false) String orderuserid,
            @RequestParam(value = "orderBy", required = false, defaultValue = "orderid") String orderBy,
            @RequestParam(value = "order", required = false, defaultValue = "asc") String order) {

        Page<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        if (orderuserid != null && !orderuserid.isEmpty()) {
            queryWrapper.like("orderuserid", orderuserid);
        }

        if ("desc".equals(order)) {
            queryWrapper.orderByDesc(orderBy);
        } else {
            queryWrapper.orderByAsc(orderBy);
        }
        return orderMapper.selectPage(page, queryWrapper);
    }
}
