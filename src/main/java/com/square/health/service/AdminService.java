package com.square.health.service;

import com.square.health.dto.AdminDto;
import com.square.health.model.Admin;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface AdminService {
    Admin getAdmin(String bloggerEmail);

    JSONObject createAdmin(HttpServletRequest httpServletRequest, AdminDto requestBodyDto) throws JSONException;
}
