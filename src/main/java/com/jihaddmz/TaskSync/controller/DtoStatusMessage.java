package com.jihaddmz.TaskSync.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class DtoStatusMessage {

    private String status;
    private String message;

    public DtoStatusMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
