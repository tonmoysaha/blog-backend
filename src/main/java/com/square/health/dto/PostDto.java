package com.square.health.dto;

import com.square.health.model.Blogger;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PostDto {

    String postId;

    @NotNull(message = "bloggerName cannot be null")
    @NotEmpty(message = "bloggerName cannot be empty")
    @NotBlank(message = "bloggerName cannot be blank")
    private String bloggerName;

    @NotBlank(message = "601")
    @Pattern(regexp = "^\\d+$", message = "Enter Valid id")
    String bloggerId;

    @NotNull(message = "postBody cannot be null")
    @NotEmpty(message = "postBody cannot be empty")
    @NotBlank(message = "postBody cannot be blank")
    private String postBody;

    private String status;

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(String bloggerId) {
        this.bloggerId = bloggerId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }
}
