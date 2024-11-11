package com.jihaddmz.TaskSync.repository;

import com.jihaddmz.TaskSync.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.ui.Model;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface RepositoryUser extends JpaRepository<ModelUser, Long> {

    Optional<ModelUser> findByUsername(String username);
}
