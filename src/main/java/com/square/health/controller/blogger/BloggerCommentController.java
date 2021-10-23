package com.square.health.controller.blogger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.dto.CommentDto;
import com.square.health.dto.PostDto;
import com.square.health.service.CommentService;
import com.square.health.util.Utility;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogger/comment")
public class BloggerCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utility utility;

    @GetMapping("/all")
    public ResponseEntity<List<CommentDto>> getAllActivePost(HttpServletRequest httpServletRequest, @RequestParam("postId") Long postId) throws JsonProcessingException, JSONException {
        List<CommentDto> post = this.commentService.getAllActiveComment(httpServletRequest, postId);
        return  ResponseEntity.ok(post);
    }

    @PostMapping("/create")
    public JsonNode createComment(HttpServletRequest httpServletRequest,
                                  @Valid @RequestBody CommentDto requestBodyDto, Errors errors) throws JsonProcessingException, JSONException {
        if (errors.hasFieldErrors()) {
            List<String> collect = errors.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            JSONObject missing_field = utility.createResponse(401, "Missing field", collect.toString());
            return objectMapper.readTree(missing_field.toString());
        }
        JSONObject post = this.commentService.createComment(httpServletRequest, requestBodyDto);
        return objectMapper.readTree(post.toString());
    }

}
