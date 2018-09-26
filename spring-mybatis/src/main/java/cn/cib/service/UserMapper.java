package cn.cib.service;

import cn.cib.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author clyde lou
 */
@Service
public interface UserMapper {
    @Select("select * from cib_user where id = #{id}")
    /**
     * 或者使用Results来映射
     @Results(
     {
     @Result(property = "createTime", column = "create_time"),
     @Result(property = "userName", column = "user_name")
     }
     )
     */
    User findUserById(@Param("id") int id);

    @Select("select * from cib_user")
    List<User> findAllUsers();

    @Insert("insert into cib_user (user_name,create_time) values(#{userName},#{createTime})")
    void addUser(User user);

    @Update("update cib_user set user_name=#{userName},create_time=#{createTime} where id = #{id}")
    void updateUser(User user);
}
