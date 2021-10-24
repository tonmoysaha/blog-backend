package com.square.health.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BloggerDto {

    String bloggerId;

    @NotNull(message = "bloggerName cannot be null")
    @NotEmpty(message = "bloggerName cannot be empty")
    @NotBlank(message = "bloggerName cannot be blank")
    private String bloggerName;
    @NotEmpty(message = "bloggerPassword cannot be empty")
    @NotNull(message = "bloggerPassword cannot be null")
    @NotBlank(message = "bloggerPassword cannot be blank")
    private String bloggerPassword;

    @Email(message = "is not valid email address")
    private String email;

    private String bloggerStatus;

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }

    public String getBloggerPassword() {
        return bloggerPassword;
    }

    public void setBloggerPassword(String bloggerPassword) {
        this.bloggerPassword = bloggerPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloggerStatus() {
        return bloggerStatus;
    }

    public void setBloggerStatus(String bloggerStatus) {
        this.bloggerStatus = bloggerStatus;
    }

    public String getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(String bloggerId) {
        this.bloggerId = bloggerId;
    }
}
