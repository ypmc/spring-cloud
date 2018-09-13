package main.cn.icbc.service;

import main.cn.icbc.bean.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "config-server", fallback = FindServiceHystrix.class)
public interface FindService {

    @GetMapping(value = "s")
    String helloServer(@RequestParam("username") String username);

    @PostMapping(value = "u")
    String helloUser(@RequestBody User user);

}
