package com.square.health.service;

import com.square.health.dto.PostDto;
import com.square.health.dto.PostStatusDto;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PostService {
    JSONObject createPost(HttpServletRequest httpServletRequest, PostDto requestBodyDto) throws JSONException;

    JSONObject deletePost(HttpServletRequest httpServletRequest, Long postId) throws JSONException;

    JSONObject approvePost(HttpServletRequest httpServletRequest, PostStatusDto postStatusDto) throws JSONException;

    JSONObject deletePostForBlogger(HttpServletRequest httpServletRequest, Long postId, Long bloggerId) throws JSONException;

    List<PostDto> getAllActivePost(HttpServletRequest httpServletRequest);

    List<PostDto> getAllPost(HttpServletRequest httpServletRequest);

    List<PostDto> myAllActivePost(HttpServletRequest httpServletRequest, Long bloggerId);
}
