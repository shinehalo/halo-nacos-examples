package com.halo.nacos.example.spring.cloud.dao;

import com.halo.nacos.example.spring.cloud.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description
 * @Author halo
 * @Date 2021/12/17
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
