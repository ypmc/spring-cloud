package cn.cib.controller;

import cn.cib.model.User;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class Controller {

    @GetMapping(value = "s")
    String helloServer(String username) {
        return "Hello " + username + "! from config server";
    }

    @PostMapping(value = "u")
    String helloUser(@RequestBody User user) {
        return "Hello " + user + "! from config server";
    }

}
