package com.jihaddmz.TaskSync.model;

import com.jihaddmz.TaskSync.controller.DtoUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
public class ModelUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    public ModelUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ModelUser(DtoUser dtoUser) {
        this.username = dtoUser.getUsername();
        this.password = dtoUser.getPassword();
    }

    public ModelUser() {
    }
}
