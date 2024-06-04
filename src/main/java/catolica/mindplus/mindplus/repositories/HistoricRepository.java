package catolica.mindplus.mindplus.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import catolica.mindplus.mindplus.entity.ActionGroups;
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.entity.User;

@Repository
public interface HistoricRepository extends CrudRepository<Historic, Integer>, PagingAndSortingRepository<Historic, Integer> {
     List<Historic> findByActionGroup(ActionGroups actionGroups, Pageable pageable);

     List<Historic> findByOwner(User user, Pageable pageable);
};
