package com.example.coffeeorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.coffeeorder.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
