package catolica.mindplus.mindplus.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import catolica.mindplus.mindplus.entity.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
     Optional<User> findByName(String name);
};
