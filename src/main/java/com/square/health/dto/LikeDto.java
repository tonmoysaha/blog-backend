package com.square.health.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LikeDto {

    boolean likePost;

    @NotBlank(message = "601")
    @Pattern(regexp = "^\\d+$", message = "Enter Valid id")
    String postId;

    @NotBlank(message = "601")
    @Pattern(regexp = "^\\d+$", message = "Enter Valid id")
    String bloggerId;

    public boolean isLikePost() {
        return likePost;
    }

    public void setLikePost(boolean likePost) {
        this.likePost = likePost;
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
}
