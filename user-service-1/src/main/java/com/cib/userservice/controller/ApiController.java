package com.cib.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author clyde lou
 */
@RestController
public class ApiController {

    @GetMapping("user/{id}")
    public String findUser(@PathVariable("id") String id,HttpServletRequest request) {
        return String.format("%s found @ %s, port is %s", id, new Date().getTime(),request.getServerPort());
    }
}
