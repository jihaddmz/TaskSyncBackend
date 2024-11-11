package com.jihaddmz.TaskSync.controller;

import com.jihaddmz.TaskSync.model.ModelUser;
import com.jihaddmz.TaskSync.service.ServiceUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class ControllerUser {

    @Autowired
    private ServiceUser service;

    public ControllerUser(ServiceUser service) {
        this.service = service;
    }

    @GetMapping
    List<ModelUser> getAll() {
        return service.getAll();
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) throws NoSuchElementException {
        ModelUser modelUser = service.deleteUser(id);
        if (modelUser == null) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }
    }

    @PostMapping("/login")
    ModelUser login(@RequestBody @Valid DtoUser user) {
        Optional<ModelUser> modelUser = service.findUserByUserName(user.getUsername());
        if (modelUser.isEmpty()) {
            throw new NoSuchElementException("User with name " + user.getUsername() + " not found");
        }

        if (!user.getPassword().equals(modelUser.get().getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        modelUser.get().setId(-20L);
        modelUser.get().setPassword("");
        return modelUser.get();
    }

    @PostMapping("/signup")
    ModelUser signup(@Valid @RequestBody DtoUser user) {
        Optional<ModelUser> modelUser = service.findUserByUserName(user.getUsername());
        if (modelUser.isPresent()) {
            throw new IllegalStateException("User with name " + user.getUsername() + " already exists");
        }

        ModelUser savedUser = service.create(new ModelUser(user));
        // for security reasons
        savedUser.setId(-20L);
        savedUser.setPassword("");
        return savedUser;
    }

}
