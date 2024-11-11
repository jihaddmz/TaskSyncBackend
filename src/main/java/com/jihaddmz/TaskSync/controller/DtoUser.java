package com.jihaddmz.TaskSync.controller;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@Data
public class DtoUser {

    @Length(min = 5, max = 15, message = "Username should be greater than 5 letters and less than 15")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$", message = "Password must be at least 8 characters, including capital & small letters, and special characters")
    private String password;

    public DtoUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
