package com.jihaddmz.TaskSync.service;

import com.jihaddmz.TaskSync.controller.DtoUser;
import com.jihaddmz.TaskSync.model.ModelUser;
import com.jihaddmz.TaskSync.repository.RepositoryUser;
import jakarta.annotation.Nullable;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUser{

    @Autowired
    private RepositoryUser repo;

    public ServiceUser(RepositoryUser repo) {
        this.repo = repo;
    }

    public ModelUser create(ModelUser user) {
        return repo.save(user);
    }

    public List<ModelUser> getAll() {
        return repo.findAll();
    }

    public @Nullable ModelUser deleteUser(Long id) {
        ModelUser modelUser = repo.findById(id).orElse(null);
        if (modelUser != null) {
            repo.delete(modelUser);
            return modelUser;
        } else {
            return null;
        }
    }

    public Optional<ModelUser> findUserByUserName(String username) {
        return repo.findByUsername(username);
    }
}
