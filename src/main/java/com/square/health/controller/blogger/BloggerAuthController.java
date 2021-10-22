package com.square.health.controller.blogger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.dto.BloggerDto;
import com.square.health.dto.BloggerLoginDto;
import com.square.health.model.Blogger;
import com.square.health.service.BloggerService;
import com.square.health.service.impl.BloggerUserDetailService;
import com.square.health.util.KeyWord;
import com.square.health.util.Utility;
import com.square.health.util.enumutil.StatusEnum;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogger")
public class BloggerAuthController {

    @Autowired
    private BloggerService bloggerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Utility utility;

    @PostMapping("/registration")
    public JsonNode createBlogger(HttpServletRequest httpServletRequest,
                                  @Valid @RequestBody BloggerDto requestBodyDto, Errors errors) throws JsonProcessingException, JSONException {
        if (errors.hasFieldErrors()) {
            List<String> collect = errors.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            JSONObject missing_field = utility.createResponse(401, "Missing field", collect.toString());
            return objectMapper.readTree(missing_field.toString());
        }
        JSONObject blogger = this.bloggerService.createBlogger(httpServletRequest, requestBodyDto);
        return objectMapper.readTree(blogger.toString());
    }

    @PostMapping("/signIn")
    public JsonNode signIn(HttpServletRequest httpServletRequest,
                           @Valid @RequestBody BloggerLoginDto requestBodyDto, Errors errors) throws JsonProcessingException, JSONException {

        JSONObject jsonObject = new JSONObject();
        Optional<Blogger> bloggerOptional = this.bloggerService.getBlogger(requestBodyDto.getBloggerEmail());
        if (bloggerOptional.isPresent() && bloggerOptional.get().getStatus().equals(StatusEnum.INACTIVE)) {
             jsonObject = utility.createResponse(HttpStatus.UNAUTHORIZED.value(), "Failed", "Blogger inactive");
            return objectMapper.readTree(jsonObject.toString());
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestBodyDto.getBloggerEmail(), requestBodyDto.getBloggerPassword()));
        if (authenticate.isAuthenticated()) {
            jsonObject = utility.createResponse(HttpStatus.ACCEPTED.value(), KeyWord.SUCCESS_MESSAGE, "Log in Successfully");
        }
        return objectMapper.readTree(jsonObject.toString());
    }

}