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

}
