package com.square.health.service;

import com.square.health.dto.LikeDto;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface LikeService {
    JSONObject createLike(HttpServletRequest httpServletRequest, LikeDto likeDto);

    JSONObject getAllLikeOfPost(HttpServletRequest httpServletRequest, Long postId) throws JSONException;
}
