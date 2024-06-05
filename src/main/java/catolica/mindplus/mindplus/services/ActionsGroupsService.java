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

    public ActionGroups insert(ActionsGroupsFormDto actionGroupForm) {
        // TODO: Quando ter Keycloak pegar isso dinamicamente do token
        var user = usersRepository.findByName("user").get();

        var actionGroup = new ActionGroups();
        actionGroup.setDescription(actionGroupForm.getDescription());
        actionGroup.setOwner(user);

        return repository.save(actionGroup);
    }

    public ActionGroups update(int id, ActionsGroupsFormDto actionGroupForm) {
        // TODO: Quando ter Keycloak pegar isso dinamicamente do token;
        var user = usersRepository.findByName("user").get();

        // TODO: Check if user is owner of the item before updating;

        var oldActionGroup = this.getActionGroupById(id).get();
        oldActionGroup.setDescription(actionGroupForm.getDescription());

        return repository.save(oldActionGroup);
    }

    public ActionGroups deleteActionGroupById(int id) {
        var oldActionGroup = this.getActionGroupById(id).get();

        // TODO: Check if user is owner of the item before deleting;
        
        repository.deleteById(id);
        return oldActionGroup;
    }

    public List<Historic> getActionGroupHistoric(int actionGroupId, int page, int pageSize) {
        var actionGroup = repository.findById(actionGroupId).get();

        PageRequest pageable = PageRequest.of(page, pageSize);
        return this.historicRepository.findByActionGroup(actionGroup, pageable);
    }
}
