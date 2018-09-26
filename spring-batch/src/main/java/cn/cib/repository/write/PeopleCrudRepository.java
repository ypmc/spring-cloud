package cn.cib.repository.write;

import cn.cib.entity.write.People;
import org.springframework.data.repository.CrudRepository;

/**
 * @author clyde lou
 */
public interface PeopleCrudRepository extends CrudRepository<People, Long> {
}
