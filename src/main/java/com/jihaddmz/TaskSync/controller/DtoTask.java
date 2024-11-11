package com.jihaddmz.TaskSync.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@Data
public class DtoTask {

    @Length(min = 1, max = 10, message = "Category should be greater than 0 letters and less than 11")
    private String categoryTitle;
    private String icon;

    @Length(min = 5, max = 15, message = "Username should be greater than 4 letters and less than 16")
    private String username;

    private String taskName;

    private String priority;
}
