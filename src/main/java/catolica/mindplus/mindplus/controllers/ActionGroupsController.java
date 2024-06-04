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
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.services.ActionsGroupsService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/actiongroups")
public class ActionGroupsController {
    @Autowired
    ActionsGroupsService actionsGroupsService;

    @GetMapping("{id}")
    public ResultContainer<ActionGroups> getActionGroupById(@PathVariable("id") int id, HttpServletResponse response) {
        var result = new ResultContainer<ActionGroups>(null, new ArrayList<String>());
        var group = actionsGroupsService.getActionGroupById(id);

        if (group.isPresent()) {
            result.setResult(group.get());
        } else {
            response.setStatus(404);

            result.addErrors("Not Found");
        }

        return result;
    }

    @DeleteMapping("{id}")
    public ResultContainer<ActionGroups> deleteActionGroupById(@PathVariable("id") int id,
            HttpServletResponse response) {
        var result = new ResultContainer<ActionGroups>(null, new ArrayList<String>());

        try {
            ActionGroups group = actionsGroupsService.deleteActionGroupById(id);
            result.setResult(group);
        } catch (NoSuchElementException e) {
            response.setStatus(404);
        }

        return result;
    }

    @PutMapping("{id}")
    public ResultContainer<ActionGroups> putActionGroup(@PathVariable("id") int id,
            @RequestBody ActionsGroupsFormDto actionGroups,
            HttpServletResponse response) {
        var result = new ResultContainer<ActionGroups>(null, new ArrayList<String>());

        try {
            ActionGroups group = actionsGroupsService.update(id, actionGroups);
            result.setResult(group);
        } catch (NoSuchElementException e) {
            response.setStatus(404);
            result.addErrors("Not Found");
            return result;
        }

        return result;
    }

    @PostMapping()
    public ResultContainer<ActionGroups> insertActionGroup(@RequestBody ActionsGroupsFormDto actionGroups) {
        var result = new ResultContainer<ActionGroups>(null, new ArrayList<String>());

        var group = actionsGroupsService.insert(actionGroups);
        result.setResult(group);

        return result; 
    }

    @GetMapping("{id}/historics")
    public ResultContainer<List<Historic>> getActionGroupHistoric(@PathVariable("id") int id,
            @RequestParam("page") int page, HttpServletResponse response,
            @RequestParam("pageSize") int pageSize) {
        var result = new ResultContainer<List<Historic>>(null, new ArrayList<String>());

        try {
            var groups = actionsGroupsService.getActionGroupHistoric(id, page, pageSize);
            result.setResult(groups);
        } catch (NoSuchElementException e) {
            response.setStatus(404);
            result.addErrors("Not Found");
        }

        return result;
    }
}
