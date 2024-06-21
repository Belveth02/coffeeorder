package com.example.coffeeorder.controller;

import com.example.coffeeorder.mapper.OrderdetaMapper;
import com.example.coffeeorder.entity.Orderdeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
public class OrderdetaController {
    @Autowired
    private OrderdetaMapper orderdetaMapper;
    @GetMapping("/orderdeta")
    public List<Orderdeta> getcOrderdeta(){  return orderdetaMapper.selectList(null); }
    @PostMapping("/addorderdeta")
    public Orderdeta createOrderdeta(@RequestBody Orderdeta orderdeta) {
        orderdetaMapper.insert(orderdeta);
        return orderdeta;
    }

}