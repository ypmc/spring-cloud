package cn.cib.controller;

import cn.cib.model.User;
import cn.cib.service.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangkang lou
 */
@RestController
public class Api {
    @Value("${version}")
    private String version;
    @Autowired
    private UserMapper mapper;

    @GetMapping("version")
    public String getVersion() {
        return version;
    }

    @GetMapping("user/{id}")
    public User findUser(@PathVariable("id") int id) {
        return mapper.findUserById(id);
    }
}
