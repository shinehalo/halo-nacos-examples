package com.halo.nacos.example.spring.cloud.service;

import com.halo.nacos.example.spring.cloud.model.User;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author halo
 * @Date 2021/12/17
 */
@Service
public interface UserService {

    User findById(Long id);
}
