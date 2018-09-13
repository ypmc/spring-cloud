package main.cn.icbc.controller;

import lombok.extern.slf4j.Slf4j;
import main.cn.icbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@Slf4j
public class Controller {

    @Value("${username}")
    String username;
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        log.info("hello in config client!");
        return "Hello " + username + "!";
    }

    @GetMapping("user/{id}")
    public String findUser(@PathVariable("id") String id) {
        return userService.findUser(id);
    }

    @GetMapping("/say/{uuid}")
    public String say(@PathVariable("uuid") String uuid) {
        return "hi " + uuid;
    }

}
