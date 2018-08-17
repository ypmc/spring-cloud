package cn.cib.service;

import cn.cib.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * @author kangkang lou
 */
@Mapper
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
}
