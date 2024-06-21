package com.example.coffeeorder.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.coffeeorder.mapper.UserMapper;
import com.example.coffeeorder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        userMapper.insert(user);
        return user;
    }

    @PutMapping("/{userid}")
    public User updateUser(@PathVariable("userid") Integer userid, @RequestBody User user) {
        user.setId(userid);
        userMapper.updateById(user);
        return user;
    }

    @DeleteMapping("/{userid}")
    public int deleteUser(@PathVariable("userid") Integer userid) {
        return userMapper.deleteById(userid);
    }

    @GetMapping("/search/{username}")
    public List<User> findByName(@PathVariable("username") String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username);
        return userMapper.selectList(queryWrapper);
    }

    @GetMapping("/getById/{userid}")
    public User findByID(@PathVariable("userid") Integer userid) {
        return userMapper.selectById(userid);
    }

    @GetMapping("/findByPage")
    public IPage<User> findByPage(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("userid"); // 根据id字段降序排序
        page.addOrder(OrderItem.desc("userid")); // 添加降序排序条件
        return userMapper.selectPage(page, queryWrapper);
    }
    @PutMapping("/{userid}/changePassword")
    public ResponseEntity<?> changePassword(@PathVariable("userid") Integer userid, @RequestBody Map<String, String> passwordData) {
        try {
            User existingUser = userMapper.selectById(userid);
            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户未找到");
            }

            String currentPassword = passwordData.get("currentPassword");
            String newPassword = passwordData.get("newPassword");

            // 验证当前密码（在这里添加你的密码验证逻辑）
            if (!existingUser.getPassword().equals(currentPassword)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("当前密码不正确");
            }
            // 更新密码（确保对新密码进行哈希处理）
            existingUser.setPassword(newPassword);
            userMapper.updateById(existingUser);

            return ResponseEntity.ok("密码更新成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("修改密码失败: " + e.getMessage());
        }
    }
}