package catolica.mindplus.mindplus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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

    public List<Historic> getUserHistorics(String id) {
        return repository.findByUserId(id, null);
    }

    public Historic insertToActionGroup(int actionGroupId, HistoricFormDto historicForm) {
        var actionGroup = actionGroupRepository.findById(actionGroupId).get();
        String userId = getUserIdFromToken(); 
        var historic = new Historic();
        historic.setWeight(historicForm.getWeight());
        historic.setReward(historicForm.getReward());
        historic.setStartDate(historicForm.getStartDate());
        historic.setEndDate(historicForm.getEndDate());
        historic.setActionGroup(actionGroup);
        historic.setUserId(userId);

        return repository.save(historic);
    }

    public Historic update(int actionGroupId, int id, HistoricFormDto historicForm) {
        var oldhistoric = this.gethistoricById(id).get();
        String userId = getUserIdFromToken(); 
        var oldHistoric = oldhistoric;
        oldHistoric.setWeight(historicForm.getWeight());
        oldHistoric.setReward(historicForm.getReward());
        oldHistoric.setStartDate(historicForm.getStartDate());
        oldHistoric.setEndDate(historicForm.getEndDate());
        oldHistoric.setUserId(userId);

        return repository.save(oldHistoric);
    }

    public Historic deletehistoricById(int actionGroupId, int id) {
        var oldhistoric = this.gethistoricById(id).get();

        repository.deleteById(id);
        return oldhistoric;
    }

    private String getUserIdFromToken() {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = authenticationToken.getToken();
        return jwt.getSubject();
    }
}
