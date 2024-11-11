CREATE TABLE model_category
(
    category_id BIGINT AUTO_INCREMENT NOT NULL,
    title       VARCHAR(255)          NULL,
    icon        VARCHAR(255)          NULL,
    user_id     BIGINT                NULL,
    CONSTRAINT pk_modelcategory PRIMARY KEY (category_id)
);

CREATE TABLE model_category_tasks
(
    model_category_category_id BIGINT NOT NULL,
    tasks_task_id              BIGINT NOT NULL
);

CREATE TABLE model_task
(
    task_id   BIGINT AUTO_INCREMENT NOT NULL,
    task_name VARCHAR(255)          NULL,
    priority  VARCHAR(255)          NULL,
    icon      VARCHAR(255)          NULL,
    is_done   BIT(1)                NOT NULL,
    user_id   BIGINT                NOT NULL,
    CONSTRAINT pk_modeltask PRIMARY KEY (task_id)
);

CREATE TABLE model_user
(
    user_id  BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255)          NULL,
    password VARCHAR(255)          NULL,
    CONSTRAINT pk_modeluser PRIMARY KEY (user_id)
);

ALTER TABLE model_category_tasks
    ADD CONSTRAINT uc_model_category_tasks_tasks_task UNIQUE (tasks_task_id);

ALTER TABLE model_user
    ADD CONSTRAINT uc_modeluser_username UNIQUE (username);

ALTER TABLE model_category
    ADD CONSTRAINT FK_MODELCATEGORY_ON_USER FOREIGN KEY (user_id) REFERENCES model_user (user_id);

ALTER TABLE model_task
    ADD CONSTRAINT FK_MODELTASK_ON_USER FOREIGN KEY (user_id) REFERENCES model_user (user_id);

ALTER TABLE model_category_tasks
    ADD CONSTRAINT fk_modcattas_on_model_category FOREIGN KEY (model_category_category_id) REFERENCES model_category (category_id);

ALTER TABLE model_category_tasks
    ADD CONSTRAINT fk_modcattas_on_model_task FOREIGN KEY (tasks_task_id) REFERENCES model_task (task_id);