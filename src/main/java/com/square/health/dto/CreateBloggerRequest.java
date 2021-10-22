package com.square.health.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CreateBloggerRequest {

    @NotNull(message = "bloggerPassword cannot be null")
    private String bloggerName;
    @NotNull(message = "bloggerPassword cannot be null")
    private String bloggerPassword;

    @Email(message = "is not valid email address")
    private String email;

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
}
