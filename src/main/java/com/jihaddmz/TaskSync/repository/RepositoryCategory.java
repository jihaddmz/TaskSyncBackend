package com.jihaddmz.TaskSync.repository;

import com.jihaddmz.TaskSync.model.ModelCategory;
import com.jihaddmz.TaskSync.model.ModelTask;
import com.jihaddmz.TaskSync.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface RepositoryCategory extends JpaRepository<ModelCategory, Long> {

    Optional<ModelCategory> findByTitleAndUser(String title, ModelUser user);

    List<ModelCategory> findAllByUser(ModelUser user);

    ModelCategory findByTasksContains(ModelTask modelTask);
}
