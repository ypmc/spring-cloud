package com.cib.userservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author clyde lou
 */
@RestController
@Api(value = "用户接口", description = "用户接口", tags = {"用户相关接口"})
public class ApiController {

    @GetMapping("user/{id}")
    @ApiOperation(value = "用户查找", notes = "用户查找")
    @ApiResponse(code = 200, message = "返回目标用户")
    public String findUser(@PathVariable("id") String id, HttpServletRequest request) {
        return String.format("%s found @ %s, port is %s", id, new Date().getTime(), request.getServerPort());
    }
}
