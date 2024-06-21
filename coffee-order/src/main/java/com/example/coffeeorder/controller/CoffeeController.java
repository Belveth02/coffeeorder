package com.example.coffeeorder.controller;
import com.example.coffeeorder.entity.Cart;
import com.example.coffeeorder.mapper.CoffMapper;
import com.example.coffeeorder.entity.Coff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
public class CoffeeController {
    @Autowired
    private CoffMapper coffMapper;
    @GetMapping("/coffee")
    public List<Cart> getcoff(){  return coffMapper.selectList(null); }
    @PostMapping("/addcoffee")
    public Coff createCoff(@RequestBody Coff coff) {
        coffMapper.insert(coff);
        return coff;
    }
}