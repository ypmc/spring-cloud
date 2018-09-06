package cn.cib.repository.read;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.cib.entity.read.User;



public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
}
