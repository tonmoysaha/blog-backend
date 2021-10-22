package com.square.health.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.dto.CreateBloggerRequest;
import com.square.health.service.BloggerService;
import com.square.health.util.Utility;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BloggerAuthController {

    @Autowired
    private BloggerService bloggerService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Utility utility;

    @PostMapping("/disable")
    public JsonNode createBlogger(HttpServletRequest httpServletRequest,
                                  @Valid @RequestBody CreateBloggerRequest requestBodyDto, Errors errors) throws JsonProcessingException, JSONException {
        if (errors.hasFieldErrors()) {
            List<String> collect = errors.getFieldErrors().stream().map(FieldError::toString).collect(Collectors.toList());
            JSONObject missing_field = utility.createResponse(401, "Missing field", collect.toString());
            return objectMapper.readTree(missing_field.toString());
        }
//        this.bloggerService.createBlogger(httpServletRequest, requestBodyDto);
//        return objectMapper.readTree(jsonObject.toString());
        return null;
    }

}
