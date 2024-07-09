package catolica.mindplus.mindplus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import catolica.mindplus.mindplus.dtos.ActionsGroupsFormDto;
import catolica.mindplus.mindplus.entity.ActionGroups;
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.repositories.ActionsGroupsRepository;
import catolica.mindplus.mindplus.repositories.HistoricRepository;

@Service
public class ActionsGroupsService {

    @Autowired
    ActionsGroupsRepository repository;

    @Autowired
    HistoricRepository historicRepository;

    public Optional<ActionGroups> getActionGroupById(int id) {
        return repository.findById(id);
    }

    public List<ActionGroups> getUserActionGroups(String id) {
        return repository.findByUserId(id, null);
    }

    public ActionGroups insert(ActionsGroupsFormDto actionGroupForm) {
        var actionGroup = new ActionGroups();
        actionGroup.setDescription(actionGroupForm.getDescription());
        String userId = getUserIdFromToken();
        actionGroup.setUserId(userId);
    
        return repository.save(actionGroup);
    }

    public ActionGroups update(int id, ActionsGroupsFormDto actionGroupForm) {
        var oldActionGroup = this.getActionGroupById(id).get();
        String userId = getUserIdFromToken(); 
        oldActionGroup.setDescription(actionGroupForm.getDescription());
        oldActionGroup.setUserId(userId);

        return repository.save(oldActionGroup);
    }

    public ActionGroups deleteActionGroupById(int id) {
        var oldActionGroup = this.getActionGroupById(id).get();
        
        repository.deleteById(id);
        return oldActionGroup;
    }

    public List<Historic> getActionGroupHistoric(int actionGroupId, int page, int pageSize) {
        var actionGroup = repository.findById(actionGroupId).get();

        PageRequest pageable = PageRequest.of(page, pageSize);
        return this.historicRepository.findByActionGroup(actionGroup, pageable);
    }


    private String getUserIdFromToken() {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = authenticationToken.getToken();
        return jwt.getSubject();
    }
}
