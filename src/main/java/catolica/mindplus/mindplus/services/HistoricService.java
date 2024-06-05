package catolica.mindplus.mindplus.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catolica.mindplus.mindplus.dtos.HistoricFormDto;
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.repositories.ActionsGroupsRepository;
import catolica.mindplus.mindplus.repositories.HistoricRepository;
import catolica.mindplus.mindplus.repositories.UsersRepository;

@Service
public class HistoricService {

    @Autowired
    ActionsGroupsRepository actionGroupRepository;

    @Autowired
    HistoricRepository repository;

    @Autowired
    UsersRepository usersRepository;

    public Optional<Historic> gethistoricById(int id) {
        return repository.findById(id);
    }

    public Historic insertToActionGroup(int actionGroupId, HistoricFormDto historicForm) {
        // TODO: Quando ter Keycloak pegar isso dinamicamente do token
        var user = usersRepository.findByName("user").get();

        // TODO: Check if user is owner of the actiongroup before inserting;

        var actionGroup = actionGroupRepository.findById(actionGroupId).get();

        var historic = new Historic();
        historic.setWeight(historicForm.getWeight());
        historic.setReward(historicForm.getReward());
        historic.setDate(historicForm.getDate());
        historic.setActionGroup(actionGroup);
        historic.setOwner(user);

        return repository.save(historic);
    }

    public Historic update(int actionGroupId, int id, HistoricFormDto historicForm) {
        // TODO: Check if user is owner of the actiongroup before inserting;
        
        var oldhistoric = this.gethistoricById(id).get();

        var oldHistoric = oldhistoric;
        oldHistoric.setWeight(historicForm.getWeight());
        oldHistoric.setReward(historicForm.getReward());
        oldHistoric.setDate(historicForm.getDate());

        return repository.save(oldHistoric);
    }

    public Historic deletehistoricById(int actionGroupId, int id) {
        // TODO: Check if user is owner of the actiongroup before inserting;
        
        var oldhistoric = this.gethistoricById(id).get();

        repository.deleteById(id);
        return oldhistoric;
    }
}
