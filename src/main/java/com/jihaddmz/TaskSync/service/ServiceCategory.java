package com.jihaddmz.TaskSync.service;

import com.jihaddmz.TaskSync.model.ModelCategory;
import com.jihaddmz.TaskSync.model.ModelTask;
import com.jihaddmz.TaskSync.model.ModelUser;
import com.jihaddmz.TaskSync.repository.RepositoryCategory;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCategory {

    @Autowired
    private RepositoryCategory repo;

    @Autowired
    private ServiceUser serviceUser;

    public ServiceCategory(RepositoryCategory repo) {
        this.repo = repo;
    }

    public ModelCategory create(String title, String icon, ModelUser user) {
        return repo.save(new ModelCategory(title, icon, user));
    }

    public ModelCategory save(ModelCategory category) {
        return repo.save(category);
    }

    public ModelCategory findByTask(ModelTask task) {
        return repo.findByTasksContains(task);
    }


    public Optional<ModelCategory> findByTitleAndUser(String categoryTitle, ModelUser modelUser) {
        return repo.findByTitleAndUser(categoryTitle, modelUser);
    }

    public List<ModelCategory> findAllByUser(String userName) {
        Optional<ModelUser> modelUser = serviceUser.findUserByUserName(userName);

        if (modelUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return repo.findAllByUser(modelUser.get());
    }
}
