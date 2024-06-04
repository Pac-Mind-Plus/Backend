package catolica.mindplus.mindplus.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import catolica.mindplus.mindplus.entity.ActionGroups;

@Repository
public interface ActionsGroupsRepository extends CrudRepository<ActionGroups, Integer>, PagingAndSortingRepository<ActionGroups, Integer> {
};
