package com.square.health.service;

import com.square.health.dto.BloggerDto;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface BloggerService {
    JSONObject createBlogger(HttpServletRequest httpServletRequest, BloggerDto requestBodyDto) throws JSONException;
}
