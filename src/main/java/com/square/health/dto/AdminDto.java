package com.square.health.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdminDto {

    @NotNull(message = "adminPassword cannot be null")
    @NotEmpty(message = "adminPassword cannot be empty")
    @NotBlank(message = "adminPassword cannot be blank")
    private String adminName;
    @NotEmpty(message = "adminPassword cannot be empty")
    @NotNull(message = "adminPassword cannot be null")
    @NotBlank(message = "adminPassword cannot be blank")
    private String adminPassword;

    @Email(message = "is not valid email address")
    private String email;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
