package com.jihaddmz.TaskSync.controller;

import com.jihaddmz.TaskSync.model.ModelTask;
import com.jihaddmz.TaskSync.service.ServiceTask;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class ControllerTask {

    @Autowired
    private ServiceTask service;

    @GetMapping
    List<ModelTask> getTasksByUser(@RequestParam @Length(min = 5, max = 15)
                                   String username) {
        return service.getAllByUsername(username);
    }

    @PostMapping
    ModelTask createTask(@RequestBody DtoTask task) {
        return service.create(task);
    }

    @PutMapping("/{id}")
    ModelTask updateTaskIsDone(@PathVariable int id, @RequestBody boolean isDone) {
        return service.updateTaskIsDone(id, isDone);
    }

    @DeleteMapping("/{id}")
    ModelTask deleteTask(@PathVariable int id) {
       return  service.deleteTask(id);
    }
}
