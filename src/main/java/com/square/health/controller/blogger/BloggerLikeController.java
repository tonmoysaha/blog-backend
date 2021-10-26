package com.square.health.controller.blogger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.dto.LikeDto;
import com.square.health.service.LikeService;
import com.square.health.util.Utility;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogger/like")
public class BloggerLikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utility utility;


    @PostMapping("/create")
    public JsonNode createLike(HttpServletRequest httpServletRequest,
                               @Valid @RequestBody LikeDto likeDto, Errors errors) throws JsonProcessingException, JSONException {
        if (errors.hasFieldErrors()) {
            List<String> collect = errors.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            JSONObject missing_field = utility.createResponse(401, "Missing field", collect.toString());
            return objectMapper.readTree(missing_field.toString());
        }
        JSONObject post = this.likeService.createLike(httpServletRequest, likeDto);
        return objectMapper.readTree(post.toString());
    }

    @GetMapping("/get")
    public JsonNode getAllLikeOfPost(HttpServletRequest httpServletRequest,
                                @RequestParam("postId") Long postId) throws JsonProcessingException, JSONException {
        JSONObject post = this.likeService.getAllLikeOfPost(httpServletRequest, postId);
        return objectMapper.readTree(post.toString());
    }

}
