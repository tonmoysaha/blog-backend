package com.square.health.service;

import com.square.health.dto.PostDto;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface PostService {
    JSONObject createPost(HttpServletRequest httpServletRequest, PostDto requestBodyDto);
}
