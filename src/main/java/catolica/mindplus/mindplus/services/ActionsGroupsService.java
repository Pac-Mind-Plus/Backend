package catolica.mindplus.mindplus.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catolica.mindplus.mindplus.dtos.ActionsGroupsFormDto;
import catolica.mindplus.mindplus.entity.ActionsGroups;
import catolica.mindplus.mindplus.repositories.ActionsGroupsRepository;

@Service
public class ActionsGroupsService {

    @Autowired
    ActionsGroupsRepository repository;

    public ActionsGroups getActionGroupById(int id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<ActionsGroups> getAllActionGroups(int page, int pageSize) {
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<ActionsGroups> actionGroups = this.repository.findAll(pageable);

        return actionGroups.toList();
    }

    public ActionsGroups insert(ActionsGroupsFormDto actionGroupForm) {
        var actionGroup = new ActionsGroups();
        actionGroup.setDescription(actionGroupForm.getDescription());

        return repository.save(actionGroup);
    }

    public ActionsGroups update(int id, ActionsGroupsFormDto actionGroupForm) {
        var oldActionGroup = this.getActionGroupById(id);

        if (oldActionGroup != null) {
            var actionGroup = new ActionsGroups();
            actionGroup.setDescription(actionGroupForm.getDescription());
            actionGroup.setId(id);

            repository.save(actionGroup);
        }

        throw new NoSuchElementException();
    }

    public ActionsGroups deleteActionGroupById(int id) {
        var oldActionGroup = this.getActionGroupById(id);

        if (oldActionGroup != null) {
            repository.deleteById(id);
            return oldActionGroup;
        }

        throw new NoSuchElementException();
    }
}
