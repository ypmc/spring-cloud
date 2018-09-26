package main.cn.icbc.service;

import main.cn.icbc.bean.User;
import org.springframework.stereotype.Component;

/**
 * @author clyde lou
 */
@Component
public class FindServiceHystrix implements FindService {
    @Override
    public String helloServer(String username) {
        return "oops, config server down for hello server " + username;
    }

    @Override
    public String helloUser(User user) {
        return "oops, config server down for hello user " + user;
    }
}
