package com.square.health.service.impl;

import com.square.health.dto.PostDto;
import com.square.health.repositoy.PostRepository;
import com.square.health.repositoy.RoleRepository;
import com.square.health.service.PostService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public JSONObject createPost(HttpServletRequest httpServletRequest, PostDto requestBodyDto) {

        return null;
    }
}
