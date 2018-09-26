package main.cn.icbc.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author clyde lou
 */
@Service
@FeignClient(value = "user-service", fallback = UserServiceHystrix.class)
public interface UserService {
    @GetMapping(value = "user/{id}")
    String findUser(@PathVariable("id") String id);
}
