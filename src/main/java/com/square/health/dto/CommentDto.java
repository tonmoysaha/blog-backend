package com.square.health.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CommentDto {

    @NotBlank(message = "commentId cannot be blank")
    @Pattern(regexp = "^\\d+$", message = "Enter Valid id")
    String commentId;

    @NotBlank(message = "postId cannot be blank")
    @Pattern(regexp = "^\\d+$", message = "Enter Valid id")
    String postId;

    @NotBlank(message = "601")
    @Pattern(regexp = "^\\d+$", message = "Enter Valid id")
    String bloggerId;

    String bloggerName;


    @NotNull(message = "comment cannot be null")
    @NotEmpty(message = "comment cannot be empty")
    @NotBlank(message = "comment cannot be blank")
    private String comment;

    String status;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(String bloggerId) {
        this.bloggerId = bloggerId;
    }

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
