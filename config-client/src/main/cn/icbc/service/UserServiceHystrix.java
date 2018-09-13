package main.cn.icbc.service;

import org.springframework.stereotype.Component;

/**
 * @author kangkang lou
 */
@Component
public class UserServiceHystrix implements UserService {
    @Override
    public String findUser(String id) {
        return "oops, user service is down! not found " + id;
    }
}