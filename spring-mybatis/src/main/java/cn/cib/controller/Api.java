package cn.cib.controller;

import cn.cib.model.User;
import cn.cib.service.UserMapper;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author clyde lou
 */
@RestController
public class Api {

    private static AtomicInteger index = new AtomicInteger();
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

    @GetMapping("users")
    public List<User> findAllUsers() {
        return mapper.findAllUsers();
    }

    @PostMapping("user")
    public boolean addUser() {
        User user = User.builder()
                .createTime(DateFormatUtils.format(Calendar.getInstance().getTime()
                        , "yyyy-MM-dd HH:mm:ss"))
                .userName(String.format("user_%s", index.getAndIncrement()))
                .build();
        mapper.addUser(user);
        return true;
    }

    @PutMapping("user")
    public boolean updateUser() {
        User user = User.builder()
                .createTime(DateFormatUtils.format(Calendar.getInstance().getTime()
                        , "yyyy-MM-dd HH:mm:ss"))
                .userName(String.format("updated_user_%s", index.get()))
                .id(index.get())
                .build();
        mapper.updateUser(user);
        return true;
    }
}
