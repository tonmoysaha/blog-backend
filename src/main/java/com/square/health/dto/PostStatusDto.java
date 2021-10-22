package com.square.health.dto;

import javax.validation.constraints.Pattern;

public class PostStatusDto {
    Long postId;
    @Pattern(regexp = "^(ACTIVE|INACTIVE)$", message = "604", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String postStatus;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }
}
