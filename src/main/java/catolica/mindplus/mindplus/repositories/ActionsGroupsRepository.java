package catolica.mindplus.mindplus.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import catolica.mindplus.mindplus.entity.ActionGroups;
import catolica.mindplus.mindplus.entity.User;

@Repository
public interface ActionsGroupsRepository extends CrudRepository<ActionGroups, Integer>, PagingAndSortingRepository<ActionGroups, Integer> {
     List<ActionGroups> findByOwner(User user, Pageable pageable);

     Optional<ActionGroups> findByOwnerAndId(User user, int id);
};
