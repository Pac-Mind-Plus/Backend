package catolica.mindplus.mindplus.controllers;

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
import catolica.mindplus.mindplus.entity.ActionsGroups;
import catolica.mindplus.mindplus.services.ActionsGroupsService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/actiongroups")
public class ActionGroupsController {

    @Autowired
    ActionsGroupsService actionsGroupsService;

     @GetMapping()
     public List<ActionsGroups> getAllActionGroups(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
         return actionsGroupsService.getAllActionGroups(page, pageSize);
     }

    @GetMapping("{id}")
    public ActionsGroups getActionGroupById(@PathVariable("id") int id, HttpServletResponse response) {
        var result = actionsGroupsService.getActionGroupById(id);

        if (result == null) {
            response.setStatus(404);
        }

        return result;
    }

    @DeleteMapping("{id}")
    public ActionsGroups deleteActionGroupById(@PathVariable("id") int id, HttpServletResponse response) {
        ActionsGroups result;
        try {
            result = actionsGroupsService.deleteActionGroupById(id);
        } catch (NoSuchElementException e) {
            response.setStatus(404);
            return null;
        }

        return result;
    }

    @PutMapping("{id}")
    public ActionsGroups putActionGroup(@PathVariable("id") int id, @RequestBody ActionsGroupsFormDto actionGroups,
            HttpServletResponse response) {
        ActionsGroups result;
        try {
            result = actionsGroupsService.update(id, actionGroups);
        } catch (NoSuchElementException e) {
            response.setStatus(404);
            return null;
        }

        return result;
    }

    @PostMapping()
    public void saveOrUpdate(@RequestBody ActionsGroupsFormDto actionGroups) {
        actionsGroupsService.insert(actionGroups);
    }

}
