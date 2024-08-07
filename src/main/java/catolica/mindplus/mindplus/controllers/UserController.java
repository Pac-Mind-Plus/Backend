package catolica.mindplus.mindplus.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import catolica.mindplus.mindplus.dtos.ResultContainer;
import catolica.mindplus.mindplus.entity.ActionGroups;
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.services.ActionsGroupsService;
import catolica.mindplus.mindplus.services.HistoricService;
import catolica.mindplus.mindplus.services.UserService;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private ActionsGroupsService actionGroupService;

    @Autowired
    private HistoricService historicService;

    @GetMapping("{id}")
    public JsonNode getUserById(@PathVariable String id) throws JsonProcessingException{
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/action-groups")
    public List<ActionGroups> getUserActionGroups(@PathVariable String id) {
        return actionGroupService.getUserActionGroups(id);
    }

    @GetMapping("/{id}/historics")
    public List<Historic> getUserHistorics(@PathVariable String id) {
        return historicService.getUserHistorics(id);
    }


    // @GetMapping("{id}")
    // public ResultContainer<User> getUserById(@PathVariable("id") int id,
    //         HttpServletResponse response) {
    //     var result = new ResultContainer<User>(null, new ArrayList<String>());
    //     var user = userService.getById(id);

    //     if (user.isPresent()) {
    //         result.setResult(user.get());
    //     } else {
    //         response.setStatus(404);

    //         result.addErrors("Not Found");
    //     }

    //     return result;
    // }

    // @GetMapping("{id}/diarys")
    // public ResultContainer<List<Historic>> getUserHistoric(@PathVariable("id") int id,
    //         @RequestParam("page") int page, HttpServletResponse response,
    //         @RequestParam("pageSize") int pageSize) {
    //     var result = new ResultContainer<List<Historic>>(null, new ArrayList<String>());

    //     try {
    //         var groups = userService.getUserHitoricPaginated(id, page, pageSize);
    //         result.setResult(groups);
    //     } catch (NoSuchElementException e) {
    //         response.setStatus(404);
    //         result.addErrors("Not Found");
    //     }

    //     return result;
    // }

    // @GetMapping("{id}/actiongroups")
    // public ResultContainer<List<ActionGroups>> getUserActionGroups(@PathVariable("id") int id,
    //         @RequestParam("page") int page, HttpServletResponse response,
    //         @RequestParam("pageSize") int pageSize) {
    //     var result = new ResultContainer<List<ActionGroups>>(null, new ArrayList<String>());

    //     try {
    //         var groups = userService.getUserActionGroupsPaginated(id, page, pageSize);
    //         result.setResult(groups);
    //     } catch (NoSuchElementException e) {
    //         response.setStatus(404);
    //         result.addErrors("Not Found");
    //     }

    //     return result;
    // }
}
