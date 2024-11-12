package com.jihaddmz.TaskSync.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
public class ModelTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    private String taskName;
    private String priority;
    private String icon;
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ModelUser user;

    public ModelTask(String taskName, String priority, ModelUser modelUser, String icon) {
        this.taskName = taskName;
        this.priority = priority;
        this.icon = icon;
        this.user = modelUser;
        this.isDone = false;
    }

    public ModelTask() {
    }

}
