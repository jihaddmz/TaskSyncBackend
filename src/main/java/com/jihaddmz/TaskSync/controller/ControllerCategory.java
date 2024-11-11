package com.jihaddmz.TaskSync.controller;

import com.jihaddmz.TaskSync.model.ModelCategory;
import com.jihaddmz.TaskSync.service.ServiceCategory;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ControllerCategory {

    @Autowired
    private ServiceCategory serviceCategory;

    @GetMapping
    List<ModelCategory> getAllCategoriesByUsername(@RequestParam @Length(min = 5, max = 15) String username) {
        return serviceCategory.findAllByUser(username);
    }
}
