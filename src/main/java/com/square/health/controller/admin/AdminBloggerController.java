package com.square.health.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.dto.AdminDto;
import com.square.health.dto.BloggerDto;
import com.square.health.dto.BloggerStatusDto;
import com.square.health.service.BloggerService;
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
@RequestMapping("/admin")
public class AdminBloggerController {

    @Autowired
    private BloggerService bloggerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utility utility;

    @PostMapping("/approve/blogger")
    public JsonNode updateBloggerStatus(HttpServletRequest httpServletRequest,
                                  @RequestBody BloggerStatusDto bloggerStatusDto) throws JsonProcessingException, JSONException {
        JSONObject blogger = this.bloggerService.approveBlogger(httpServletRequest, bloggerStatusDto);
        return objectMapper.readTree(blogger.toString());
    }

    @GetMapping("/all/blogger")
    public ResponseEntity<List<BloggerDto>> getAllAdmin(HttpServletRequest httpServletRequest) throws JsonProcessingException, JSONException {
        List<BloggerDto> post = this.bloggerService.getAllBlogger(httpServletRequest);
        return  ResponseEntity.ok(post);
    }
}
