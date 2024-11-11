package com.jihaddmz.TaskSync.repository;

import com.jihaddmz.TaskSync.model.ModelTask;
import com.jihaddmz.TaskSync.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface RepositoryTask extends JpaRepository<ModelTask, Long> {
    List<ModelTask> findAllByUser(ModelUser user);
}
