package catolica.mindplus.mindplus.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catolica.mindplus.mindplus.dtos.ActionsGroupsFormDto;
import catolica.mindplus.mindplus.entity.ActionGroups;
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.repositories.ActionsGroupsRepository;
import catolica.mindplus.mindplus.repositories.HistoricRepository;
import catolica.mindplus.mindplus.repositories.UsersRepository;

@Service
public class ActionsGroupsService {

    @Autowired
    ActionsGroupsRepository repository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    HistoricRepository historicRepository;

    public Optional<ActionGroups> getActionGroupById(int id) {
        return repository.findById(id);
    }

    public List<ActionGroups> getAllActionGroups(int page, int pageSize) {
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<ActionGroups> actionGroups = this.repository.findAll(pageable);

        return actionGroups.toList();
    }

    public ActionGroups insert(ActionsGroupsFormDto actionGroupForm) {
        // TODO: Quando ter Keycloak pegar isso dinamicamente do token
        var user = usersRepository.findByName("user").get();

        var actionGroup = new ActionGroups();
        actionGroup.setDescription(actionGroupForm.getDescription());
        actionGroup.setOwner(user);

        return repository.save(actionGroup);
    }

    public ActionGroups update(int id, ActionsGroupsFormDto actionGroupForm) {
        var oldActionGroup = this.getActionGroupById(id);

        if (oldActionGroup.isPresent()) {
            var actionGroup = new ActionGroups();
            actionGroup.setDescription(actionGroupForm.getDescription());
            actionGroup.setId(id);

            return repository.save(actionGroup);
        }

        throw new NoSuchElementException();
    }

    public ActionGroups deleteActionGroupById(int id) {
        var oldActionGroup = this.getActionGroupById(id);

        if (oldActionGroup.isPresent()) {
            repository.deleteById(id);
            return oldActionGroup.get();
        }

        throw new NoSuchElementException();
    }

    public List<Historic> getActionGroupHistoric(int actionGroupId, int page, int pageSize) {
        var actionGroup = repository.findById(actionGroupId);

        if (actionGroup.isPresent()) {
            PageRequest pageable = PageRequest.of(page, pageSize);
            return this.historicRepository.findByActionGroup(actionGroup.get(), pageable);
        };

        throw new NoSuchElementException();
    }
}
