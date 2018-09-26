package cn.cib.repository.read;

import cn.cib.entity.read.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author clyde lou
 */
public interface UserReaderRepository extends PagingAndSortingRepository<User, Long> {
    @Override
    Page<User> findAll(Pageable pageable);

    Page<User> findAllByFirstNameLike(String firstName, Pageable pageable);
}
