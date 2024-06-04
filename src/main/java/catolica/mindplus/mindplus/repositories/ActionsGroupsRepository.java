package catolica.mindplus.mindplus.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import catolica.mindplus.mindplus.entity.ActionsGroups;

@Repository
public interface ActionsGroupsRepository extends CrudRepository<ActionsGroups, Integer>, PagingAndSortingRepository<ActionsGroups, Integer> {
};
