package com.example.coffeeorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.coffeeorder.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoffMapper extends BaseMapper<Cart> {
}
