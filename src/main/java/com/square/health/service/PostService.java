package com.square.health.service;

import com.square.health.dto.PostDto;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface PostService {
    JSONObject createPost(HttpServletRequest httpServletRequest, PostDto requestBodyDto) throws JSONException;

    JSONObject deletePost(HttpServletRequest httpServletRequest, Long postId) throws JSONException;
}
