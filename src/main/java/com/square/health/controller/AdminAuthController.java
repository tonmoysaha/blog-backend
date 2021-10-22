package com.square.health.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.dto.AdminLoginDto;
import com.square.health.dto.BloggerLoginDto;
import com.square.health.model.Admin;
import com.square.health.service.AdminService;
import com.square.health.util.KeyWord;
import com.square.health.util.Utility;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Utility utility;

    @PostMapping("/signIn")
    public JsonNode signIn(HttpServletRequest httpServletRequest,
                           @Valid @RequestBody AdminLoginDto requestBodyDto, Errors errors) throws JsonProcessingException, JSONException {

        JSONObject jsonObject = new JSONObject();
        Admin admin = this.adminService.getAdmin(requestBodyDto.getAdminEmail());
        if (admin != null) {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestBodyDto.getAdminEmail(), requestBodyDto.getAdminPassword()));
            if (authenticate.isAuthenticated()) {
                jsonObject = utility.createResponse(HttpStatus.ACCEPTED.value(), KeyWord.SUCCESS_MESSAGE, "Log in Successfully");
            }
        }
        return objectMapper.readTree(jsonObject.toString());
    }
}
