package com.jihaddmz.TaskSync.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class ModelCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;


    private String title;
    private String icon;

    @OneToMany(targetEntity = ModelTask.class)
    private List<ModelTask> tasks;

    @ManyToOne(targetEntity = ModelUser.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private ModelUser user;

    public ModelCategory(String title, String icon, ModelUser user) {
        this.title = title;
        this.icon = icon;
        this.user = user;
        this.tasks = new ArrayList<>();
    }

    public ModelCategory() {
    }
}
