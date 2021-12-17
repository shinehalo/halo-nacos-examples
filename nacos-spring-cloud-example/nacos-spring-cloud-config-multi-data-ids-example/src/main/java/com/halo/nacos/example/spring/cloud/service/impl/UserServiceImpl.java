package com.halo.nacos.example.spring.cloud.service.impl;

import com.halo.nacos.example.spring.cloud.dao.UserRepository;
import com.halo.nacos.example.spring.cloud.model.User;
import com.halo.nacos.example.spring.cloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author halo
 * @Date 2021/12/17
 */
@Service
@RefreshScope
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final RedisTemplate redisTemplate;

    @Value("${app.user.cache}")
    private boolean cache;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RedisTemplate redisTemplate) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User findById(Long id) {
        logger.info("cache: {}", cache);

        if (cache) {
            Object obj = redisTemplate.opsForValue().get(key(id));
            if (obj != null) {
                logger.info("get user from cache, id: {}", id);
                return (User) obj;
            }
        }

        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (cache) {
                logger.info("set cache for user, id: {}", id);
                redisTemplate.opsForValue().set(key(id), user);
            }
        }

        return user;
    }

    private String key(Long id) {
        return String.format("nacos-spring-cloud-config-multi-data-ids-example:user:%d", id);
    }
}
