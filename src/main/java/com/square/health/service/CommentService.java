package com.square.health.service;

import com.square.health.dto.CommentDto;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommentService {
    List<CommentDto> getAllActiveComment(HttpServletRequest httpServletRequest, Long postId);

    JSONObject createComment(HttpServletRequest httpServletRequest, CommentDto requestBodyDto) throws JSONException;
}
