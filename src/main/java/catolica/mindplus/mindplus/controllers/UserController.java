package catolica.mindplus.mindplus.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import catolica.mindplus.mindplus.dtos.ActionsGroupsFormDto;
import catolica.mindplus.mindplus.dtos.ResultContainer;
import catolica.mindplus.mindplus.entity.ActionGroups;
import catolica.mindplus.mindplus.services.ActionsGroupsService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    ActionsGroupsService actionsGroupsService;

    // @GetMapping()
    // public ResultContainer<List<ActionGroups>> getAllActionGroups(@RequestParam("page") int page,
    //         @RequestParam("pageSize") int pageSize) {
    //     var result = new ResultContainer<List<ActionGroups>>(null, new ArrayList<String>());

    //     var groups = actionsGroupsService.getAllActionGroups(page, pageSize);

    //     result.setResult(groups);

    //     return result;
    // }

    // @GetMapping("{id}")
    // public ResultContainer<ActionGroups> getActionGroupById(@PathVariable("id") int id, HttpServletResponse response) {
    //     var result = new ResultContainer<ActionGroups>(null, new ArrayList<String>());
    //     var group = actionsGroupsService.getActionGroupById(id);

    //     result.setResult(group);

    //     if (result == null) {
    //         response.setStatus(404);

    //         result.addErrors("Not Found");
    //     }

    //     return result;
    // }
    //
    
    @GetMapping("{user_id}/actiongroups")
    public ResultContainer<List<ActionGroups>> getAllUserActionGroups(@PathVariable("user_id") int id, @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize) {
        var result = new ResultContainer<List<ActionGroups>>(null, new ArrayList<String>());

        // TODO: call get action groups by user id
        var groups = actionsGroupsService.getAllActionGroups(page, pageSize);

        result.setResult(groups);

        return result;
    }

}
