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

import catolica.mindplus.mindplus.dtos.HistoricFormDto;
import catolica.mindplus.mindplus.dtos.ResultContainer;
import catolica.mindplus.mindplus.entity.Historic;
import catolica.mindplus.mindplus.services.ActionsGroupsService;
import catolica.mindplus.mindplus.services.HistoricService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/actiongroups/{actiongroup_id}/diarys")
public class HistoricController {

    @Autowired
    ActionsGroupsService actionsGroupsService;

    @Autowired
    HistoricService historicService;

    @PostMapping()
    public ResultContainer<Historic> insertActionGroupHistoric(@PathVariable("actiongroup_id") int actiongroupId, @RequestBody HistoricFormDto historicFormDto, HttpServletResponse response) {
        var result = new ResultContainer<Historic>(null, new ArrayList<String>());

        try {
            var historic = historicService.insertToActionGroup(actiongroupId, historicFormDto);
            result.setResult(historic);
        } catch (NoSuchElementException e) {
            response.setStatus(404);
            result.addErrors("Not Found");
        }

        return result; 
    }

    @PutMapping("{id}")
    public ResultContainer<Historic> putActionGroupHistoric(@PathVariable("id") int id, @PathVariable("actiongroup_id") int actiongroupId, @RequestBody HistoricFormDto historicFormDto, HttpServletResponse response) {
        var result = new ResultContainer<Historic>(null, new ArrayList<String>());

        try {
            var historic = historicService.update(actiongroupId, id, historicFormDto);
            result.setResult(historic);
        } catch (NoSuchElementException e) {
            response.setStatus(404);
            result.addErrors("Not Found");
        }

        return result; 
    }

    @DeleteMapping("{id}")
    public ResultContainer<Historic> deleteActionGroupHistoric(@PathVariable("id") int id, @PathVariable("actiongroup_id") int actiongroupId, HttpServletResponse response) {
        var result = new ResultContainer<Historic>(null, new ArrayList<String>());

        try {
            var historic = historicService.deletehistoricById(actiongroupId, id);
            result.setResult(historic);
        } catch (NoSuchElementException e) {
            response.setStatus(404);
            result.addErrors("Not Found");
        }

        return result; 
    }

    @GetMapping()
    public ResultContainer<List<Historic>> getActionGroupHistoric(@PathVariable("actiongroup_id") int id,
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
