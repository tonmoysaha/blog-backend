package com.square.health.dto;

import javax.validation.constraints.Pattern;

public class BloggerStatusDto {
    Long bloggerId;
    @Pattern(regexp = "^(ACTIVE|INACTIVE)$", message = "604", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String bloggerStatus;

    public Long getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(Long bloggerId) {
        this.bloggerId = bloggerId;
    }

    public String getBloggerStatus() {
        return bloggerStatus;
    }

    public void setBloggerStatus(String bloggerStatus) {
        this.bloggerStatus = bloggerStatus;
    }
}
