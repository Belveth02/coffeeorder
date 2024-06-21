package com.example.coffeeorder.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import com.baomidou.mybatisplus.annotation.*;
@Data
@Getter
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String address;
    private String userimage;
    public void setId(Integer userid) {
        this.userid = userid;
    }
    public void setPassword(String password) {

        this.password = password;
    }
}


