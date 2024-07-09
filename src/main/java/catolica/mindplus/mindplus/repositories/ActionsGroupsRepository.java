package catolica.mindplus.mindplus.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import catolica.mindplus.mindplus.entity.ActionGroups;

@Repository
public interface ActionsGroupsRepository extends CrudRepository<ActionGroups, Integer>, PagingAndSortingRepository<ActionGroups, Integer> {
     List<ActionGroups> findByUserId(String userId, Pageable pageable);
};
