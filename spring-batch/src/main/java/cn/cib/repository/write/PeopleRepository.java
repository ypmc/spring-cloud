package cn.cib.repository.write;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.cib.entity.write.People;

public interface PeopleRepository extends JpaRepository<People, Long> {
}
