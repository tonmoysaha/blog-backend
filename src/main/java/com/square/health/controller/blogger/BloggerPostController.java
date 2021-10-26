package com.square.health.controller.blogger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.dto.BloggerDto;
import com.square.health.dto.PostDto;
import com.square.health.model.Post;
import com.square.health.service.BloggerService;
import com.square.health.service.PostService;
import com.square.health.util.Utility;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/blogger/post")
public class BloggerPostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utility utility;

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllActivePost(HttpServletRequest httpServletRequest) throws JsonProcessingException, JSONException {
        List<PostDto> post = this.postService.getAllActivePost(httpServletRequest);
        return  ResponseEntity.ok(post);
    }

    @GetMapping("/my/all")
    public ResponseEntity<List<PostDto>> myAllActivePost(HttpServletRequest httpServletRequest, @RequestParam("bloggerId") Long bloggerId) throws JsonProcessingException, JSONException {
        List<PostDto> post = this.postService.myAllActivePost(httpServletRequest, bloggerId);
        return  ResponseEntity.ok(post);
    }

    @PostMapping("/create")
    public JsonNode createPost(HttpServletRequest httpServletRequest,
                                  @Valid @RequestBody PostDto requestBodyDto, Errors errors) throws JsonProcessingException, JSONException {
        if (errors.hasFieldErrors()) {
            List<String> collect = errors.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            JSONObject missing_field = utility.createResponse(401, "Missing field", collect.toString());
            return objectMapper.readTree(missing_field.toString());
        }
        JSONObject post = this.postService.createPost(httpServletRequest, requestBodyDto);
        return objectMapper.readTree(post.toString());
    }

    @DeleteMapping("/delete")
    public JsonNode deletePost(HttpServletRequest httpServletRequest,
                                @RequestParam("postId") Long postId, @RequestParam("bloggerId") Long bloggerId) throws JsonProcessingException, JSONException {
        JSONObject post = this.postService.deletePostForBlogger(httpServletRequest, postId, bloggerId);
        return objectMapper.readTree(post.toString());
    }

}
