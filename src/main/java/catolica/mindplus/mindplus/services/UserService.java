package catolica.mindplus.mindplus.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catolica.mindplus.mindplus.entity.ActionGroups;
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.entity.User;
import catolica.mindplus.mindplus.repositories.ActionsGroupsRepository;
import catolica.mindplus.mindplus.repositories.HistoricRepository;
import catolica.mindplus.mindplus.repositories.UsersRepository;

@Service
public class UserService {

    @Autowired
    UsersRepository repository;

    @Autowired
    ActionsGroupsRepository actionsGroupsRepository;

    @Autowired
    HistoricRepository historicRepository;

    public Optional<User> getById(int id) {
        return repository.findById(id);
    }

    public List<Historic> getUserHitoricPaginated(int userId, int page, int pageSize) {
        var user = repository.findById(userId);

        if (user.isPresent()) {
            PageRequest pageable = PageRequest.of(page, pageSize);
            return this.historicRepository.findByOwner(user.get(), pageable);
        };

        throw new NoSuchElementException();
    }

    public List<ActionGroups> getUserActionGroupsPaginated(int userId, int page, int pageSize) {
        var user = repository.findById(userId);

        if (user.isPresent()) {
            PageRequest pageable = PageRequest.of(page, pageSize);
            return this.actionsGroupsRepository.findByOwner(user.get(), pageable);
        };

        throw new NoSuchElementException();
    }

	public void setRepository(UsersRepository repository) {
		this.repository = repository;
	}

}
