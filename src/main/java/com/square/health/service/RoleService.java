package com.square.health.service;

import com.square.health.dto.BloggerDto;
import com.square.health.model.Role;
import com.square.health.util.enumutil.RoleEnum;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface RoleService {
    Role findRole(String role) throws JSONException;
}
