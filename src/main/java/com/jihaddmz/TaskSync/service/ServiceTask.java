package com.jihaddmz.TaskSync.service;

import com.jihaddmz.TaskSync.controller.DtoTask;
import com.jihaddmz.TaskSync.model.ModelCategory;
import com.jihaddmz.TaskSync.model.ModelTask;
import com.jihaddmz.TaskSync.model.ModelUser;
import com.jihaddmz.TaskSync.repository.RepositoryTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiceTask {

    @Autowired
    private RepositoryTask repo;

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceCategory serviceCategory;

    public ServiceTask(RepositoryTask repo) {
        this.repo = repo;
    }

    public ModelTask create(DtoTask dtoTask) throws NoSuchElementException {
        Optional<ModelUser> modelUser = serviceUser.findUserByUserName(dtoTask.getUsername());
        if (modelUser.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }

        ModelTask modelTask = repo.save(new ModelTask(dtoTask.getTaskName(), dtoTask.getPriority(), modelUser.get(), dtoTask.getIcon()));

        Optional<ModelCategory> category = serviceCategory.findByTitleAndUser(dtoTask.getCategoryTitle(), modelUser.get());
        if (category.isEmpty()) {
            ModelCategory modelCategory = new ModelCategory(dtoTask.getCategoryTitle(), dtoTask.getIcon(), modelUser.get());
            modelCategory.getTasks().add(modelTask);
            serviceCategory.save(modelCategory);
        } else {
            category.get().getTasks().add(modelTask);
            serviceCategory.save(category.get());
        }

        return modelTask;
    }

    public ModelTask updateTaskIsDone(long id, boolean isDone) throws NoSuchElementException {
        Optional<ModelTask> modelTask = repo.findById(id);

        if (modelTask.isEmpty()) {
            throw new NoSuchElementException("Task not found");
        }

        modelTask.get().setDone(isDone);

        return repo.save(modelTask.get());
    }

    public ModelTask deleteTask(long id) throws NoSuchElementException {
        Optional<ModelTask> modelTask = repo.findById(id);
        if (modelTask.isEmpty()) {
            throw new NoSuchElementException("Task not found");
        }
        ModelCategory modelCategory = serviceCategory.findByTask(modelTask.get());
        modelCategory.getTasks().remove(modelTask.get());
        serviceCategory.save(modelCategory);

        repo.delete(modelTask.get());

        return modelTask.get();
    }

    public List<ModelTask> getAllByUsername(String username) throws NoSuchElementException {
        Optional<ModelUser> modelUser = serviceUser.findUserByUserName(username);
        if (modelUser.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
        return repo.findAllByUser(modelUser.get());
    }
}
