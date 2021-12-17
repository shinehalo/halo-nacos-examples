package com.halo.nacos.example.spring.cloud.controller;

import com.halo.nacos.example.spring.cloud.model.User;
import com.halo.nacos.example.spring.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author halo
 * @Date 2021/12/17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public User get(@RequestParam long id) {
        return userService.findById(id);
    }
}
