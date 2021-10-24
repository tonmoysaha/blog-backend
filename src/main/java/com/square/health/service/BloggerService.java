package com.square.health.service;

import com.square.health.dto.AdminDto;
import com.square.health.dto.BloggerDto;
import com.square.health.dto.BloggerStatusDto;
import com.square.health.model.Blogger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface BloggerService {
    JSONObject createBlogger(HttpServletRequest httpServletRequest, BloggerDto requestBodyDto) throws JSONException;

    Optional<Blogger> getBlogger(String bloggerEmail);

    JSONObject approveBlogger(HttpServletRequest httpServletRequest, BloggerStatusDto bloggerStatusDto) throws JSONException;

    List<BloggerDto> getAllBlogger(HttpServletRequest httpServletRequest);
}
