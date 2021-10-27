package com.square.health.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.dto.PostDto;
import com.square.health.dto.PostStatusDto;
import com.square.health.service.PostService;
import com.square.health.util.Utility;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin/post")
public class AdminPostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utility utility;

    @PostMapping("/approve")
    public JsonNode updatePostStatus(HttpServletRequest httpServletRequest,
                                        @RequestBody PostStatusDto postStatusDto) throws JsonProcessingException, JSONException {
        JSONObject blogger = this.postService.approvePost(httpServletRequest, postStatusDto);
        return objectMapper.readTree(blogger.toString());
    }

    @DeleteMapping("/delete")
    public JsonNode deletePost(HttpServletRequest httpServletRequest,
                               @RequestParam("postId") Long postId) throws JsonProcessingException, JSONException {
        JSONObject post = this.postService.deletePost(httpServletRequest, postId);
        return objectMapper.readTree(post.toString());
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllAdmin(HttpServletRequest httpServletRequest) throws JsonProcessingException, JSONException {
        List<PostDto> post = this.postService.getAllPost(httpServletRequest);
        return  ResponseEntity.ok(post);
    }

}