package catolica.mindplus.mindplus.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import catolica.mindplus.mindplus.dtos.HistoricFormDto;
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.repositories.ActionsGroupsRepository;
import catolica.mindplus.mindplus.repositories.HistoricRepository;

@Service
public class HistoricService {

    @Autowired
    ActionsGroupsRepository actionGroupRepository;

    @Autowired
    HistoricRepository repository;

    public Optional<Historic> gethistoricById(int id) {
        return repository.findById(id);
    }

    public List<Historic> getAllByActionGroup(int actionGroupId, int page, int pageSize) {
        var actionGroup = actionGroupRepository.findById(actionGroupId);

        if (actionGroup.isPresent()) {
            PageRequest pageable = PageRequest.of(page, pageSize);
            return this.repository.findByActionGroup(actionGroup.get(), pageable);
        }
        ;

        throw new NoSuchElementException();
    }

    public Historic insertToActionGroup(int actionGroupId, HistoricFormDto historicForm) {
        var actionGroup = actionGroupRepository.findById(actionGroupId);

        if (actionGroup.isPresent()) {
            var historic = new Historic();
            historic.setWeight(historicForm.getWeight());
            historic.setReward(historicForm.getReward());
            historic.setDate(historicForm.getDate());
            historic.setActionGroup(actionGroup.get());

            return repository.save(historic);
        };

        throw new NoSuchElementException();
    }

    public Historic update(int actionGroupId, int id, HistoricFormDto historicForm) {
        var actionGroup = actionGroupRepository.findById(actionGroupId);

        if (actionGroup.isPresent()) {
            var oldhistoric = this.gethistoricById(id);

            if (oldhistoric.isPresent()) {
                var oldHistoric = oldhistoric.get();
                oldHistoric.setWeight(historicForm.getWeight());
                oldHistoric.setReward(historicForm.getReward());
                oldHistoric.setDate(historicForm.getDate());

                return repository.save(oldHistoric);
            }
        }

        throw new NoSuchElementException();
    }

    public Historic deletehistoricById(int actionGroupId, int id) {
        var actionGroup = actionGroupRepository.findById(actionGroupId);

        if (actionGroup.isPresent()) {
            var oldhistoric = this.gethistoricById(id);

            if (oldhistoric.isPresent()) {
                repository.deleteById(id);
                return oldhistoric.get();
            }
        }

        throw new NoSuchElementException();
    }
}
